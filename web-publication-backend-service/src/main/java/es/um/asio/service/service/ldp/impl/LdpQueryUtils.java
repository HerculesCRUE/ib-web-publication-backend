package es.um.asio.service.service.ldp.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import es.um.asio.service.config.properties.LdpEntitiesProperties;
import es.um.asio.service.config.properties.LdpEntityProperties;
import lombok.Getter;

public class LdpQueryUtils {	
	
	public enum Type {
		DATA, COUNT, GROUP
	}
	
	@Getter
	public enum Category {
		FRONT("front"), BACK("back");

		private String categoryName;

		Category(String categoryName) {
			this.categoryName = categoryName;
		}

		public static Category from(String categoryName) {
			return Stream.of(Category.values())					
					.filter(category -> category.getCategoryName().equals(categoryName))
					.findFirst()
					.orElse(null);
		}
	}	
	
	private static Map<String, String> SORT_PROPERTIES;
	
	static {
		SORT_PROPERTIES = new HashMap<String, String>();
		SORT_PROPERTIES.put("relationship", "link_result");
		SORT_PROPERTIES.put("relatedType", "related");
	}
	
	private static final String PREFIX = "prefix asio-def: <%s/def/>";
	
	private static final String SELECT_COUNT = " SELECT (count (distinct ?%s) as ?count) ";
	private static final String SELECT_GROUP = "SELECT DISTINCT (REPLACE(STR(?%s),\"%s\",\"\") as ?type)  ";
	private static final String SELECT_GROUP_UUID_REGEXP = "/[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}";
	private static final String SELECT_DATA = "SELECT DISTINCT ?%s ";
	private static final String SELECT_DATA_PROPERTY = " (GROUP_CONCAT(DISTINCT ?%s; SEPARATOR=\", \") AS ?%s%s) ";
	private static final String SELECT_RELATIONSHIP_NAME = "link";
	private static final String SELECT_ENTITY_TYPE_NAME = "related";
	private static final String SELECT_ENTITY_PROPERTY_SUFIX = "_result";
	
	private static final String GROUP = "GROUP BY ?%s ORDER BY %s(?%s) LIMIT %s  OFFSET %s";			

	private LdpQueryUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static String buildRelatedQuery(LdpEntitiesProperties ldpEntitiesProperties, String uri, Pageable pageable,
			Type queryType, Category category) {

		StringBuilder queryBuilder = new StringBuilder();
		
		queryBuilder.append(buildSelectQuery(ldpEntitiesProperties, queryType));
		queryBuilder.append(buildWhereQuery(ldpEntitiesProperties, uri, category));
		queryBuilder.append(buildGroupAndOrderQuery(queryType, pageable));		

		return queryBuilder.toString();
	}

	private static List<String> findRelations(final String uri, final Category category,
			LdpEntitiesProperties ldpEntitiesProperties) {
		List<String> relations = null;
		for (LdpEntityProperties entityProperties : ldpEntitiesProperties.getEntities()) {
			if (Arrays.stream(entityProperties.getType().split(","))
					.anyMatch(entityType -> uri.contains(entityType.trim()))) {
				relations = Category.BACK.equals(category) ? entityProperties.getBack() : entityProperties.getForward();
				break;
			}
		}

		if (relations == null) {
			relations = Collections.emptyList();
		}
		return relations;
	}

	private static String buildSelectQuery(LdpEntitiesProperties ldpEntitiesProperties, Type queryType) {
		StringBuilder selectQueryBuilder = new StringBuilder(String.format(PREFIX, ldpEntitiesProperties.getUriNamespace()));
		String selectQuery = null;
		switch (queryType) {
		case COUNT:
			selectQuery = String.format(SELECT_COUNT, SELECT_ENTITY_TYPE_NAME);
			break;
		case GROUP:
			selectQuery =  String.format(SELECT_GROUP, SELECT_ENTITY_TYPE_NAME, SELECT_GROUP_UUID_REGEXP);
			break;
		default:
			List<String> properties = new ArrayList<>(Arrays.asList(ldpEntitiesProperties.getValidProperties()));
			properties.add(SELECT_RELATIONSHIP_NAME);
			selectQuery = properties.stream().map(
					property -> String.format(SELECT_DATA_PROPERTY, property, property, SELECT_ENTITY_PROPERTY_SUFIX))
					.collect(Collectors.joining(StringUtils.SPACE, String.format(SELECT_DATA, SELECT_ENTITY_TYPE_NAME),
							StringUtils.EMPTY));
			break;
		}
		
		
		return selectQueryBuilder.append(selectQuery).toString();
	}
	
	private static String buildWhereQuery(LdpEntitiesProperties ldpEntitiesProperties, String uri, Category category) {
		List<String> queryInfo = buildDirectRelation(category);
		queryInfo.addAll(findRelations(uri, category, ldpEntitiesProperties));		
		return queryInfo.stream()
				.map(subqueryInfo -> buildSubquery(subqueryInfo, uri, ldpEntitiesProperties.getValidProperties(),
						ldpEntitiesProperties.getInvalidEntities()))
				.collect(Collectors.joining(" UNION ", " WHERE {", "}"));
	}
	
	private static String buildGroupAndOrderQuery (Type queryType, Pageable pageable) {
		StringBuilder groupQuery = new StringBuilder();
		
		if (Type.DATA.equals(queryType)) {			
			Order order = new Order(Direction.ASC, SELECT_ENTITY_TYPE_NAME);
			if (!pageable.getSort().toList().isEmpty()) {
				Order orderRecieved = pageable.getSort().toList().get(0);				
				order = new Order(orderRecieved.getDirection(), SORT_PROPERTIES.getOrDefault(orderRecieved.getProperty(), SELECT_ENTITY_TYPE_NAME));
			}			
			groupQuery.append(String.format(GROUP, SELECT_ENTITY_TYPE_NAME, order.getDirection().name(), order.getProperty(), pageable.getPageSize(), pageable.getOffset()));
		}
		
		return groupQuery.toString();
		
	}
	
	private static List<String> buildDirectRelation(Category category) {
		List<String> directRelation = new ArrayList<String>();
		directRelation.add(Category.BACK.equals(category) ? "?link:back-direct" : "?link:forward-direct");
		return directRelation;
	}
	
	private static String buildSubquery(String subqueryInfo, String uri, String[] properties, String[] invalidEntities) {
		String[] relations = subqueryInfo.split("\\|");
		String subquery = "{";

		int relationOrder = 0;
		for (String relation : relations) {
			boolean last = (relationOrder + 1) == relations.length;
			boolean first = relationOrder == 0;
			String[] relationData = relation.split(":");
			if (relationData[1].startsWith(Category.BACK.getCategoryName())) {
				subquery += " ?related" + (relationOrder + 1) + (relationData[1].equals(Category.BACK.getCategoryName()) ? " asio-def:" : " ")
						+ relationData[0] + " " + (first ? "<" + uri + ">" : "?related" + relationOrder) + ".";
			} else {
				subquery += (first ? "<" + uri + ">" : "?related" + relationOrder)
						+ (relationData[1].equals("forward") ? " asio-def:" : " ") + relationData[0] + " ?related"
						+ (relationOrder + 1) + ".";
			}
			if (first) {
				if (relationData[1].equals("back-direct") || relationData[1].equals("forward-direct")) {
					subquery += "?related" + (relationOrder + 1) + " a ?related"+(relationOrder + 2)+";";
				} else {
					subquery += "BIND (asio-def:" + relationData[0] + " as ?link)";
				}
			}

			if (last) {
				subquery += "BIND (?related" + (relationOrder + 1) + " as ?related)";
			}
			relationOrder++;
		}

		for (String key : properties) {
			subquery += " OPTIONAL {" + "    ?related" + relationOrder + " asio-def:" + key + " ?" + key + " }";
		}

		subquery += Stream.of(invalidEntities)
				.map(invalidEntity -> "!contains (str(?related), \"" + invalidEntity + "\")")
				.collect(Collectors.joining(" && ", "FILTER (", ")"));
		
		subquery += " FILTER(?related != <"+uri+">)";

		subquery += "}";

		return subquery;
	}
}
