package es.um.asio.service.service.ldp.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.domain.Pageable;

import es.um.asio.service.config.properties.LdpEntitiesProperties;
import es.um.asio.service.config.properties.LdpEntityProperties;

public class LdpQueryUtils {

	private LdpQueryUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static String buildRelatedQuery(LdpEntitiesProperties ldpEntitiesProperties, String uri, Pageable pageable,
			boolean count, String type) {

		List<String> queryInfo = buildDirectRelation(type);
		queryInfo.addAll(findRelations(uri, type, ldpEntitiesProperties));

		String query = "prefix asio-def: <" + ldpEntitiesProperties.getUriNamespace() + "/def/>";
		if (count) {
			query += " SELECT (count (distinct ?related) as ?count) ";
		} else {
			query += " SELECT DISTINCT ?related  ";
			List<String> properties = new ArrayList<>(Arrays.asList(ldpEntitiesProperties.getValidProperties()));
			properties.add("link");
			for (String key : properties) {
				query += " (GROUP_CONCAT(DISTINCT ?" + key + "; SEPARATOR=\", \") AS ?" + key + "_result) ";
			}

		}
		query += " WHERE { ";

		boolean unionPrefix = false;

		for (String subqueryInfo : queryInfo) {
			if (unionPrefix) {
				query += " UNION ";
			} else {
				unionPrefix = true;
			}

			query += buildSubquery(subqueryInfo, uri, ldpEntitiesProperties.getValidProperties(),
					ldpEntitiesProperties.getInvalidEntities());
		}

		/*
		 * Order order = new Order(Direction.ASC, "title"); if
		 * (!pageable.getSort().toList().isEmpty()) { order =
		 * pageable.getSort().toList().get(0); }
		 */

		query += "} ";

		if (!count) {
			query += "GROUP BY ?related ORDER BY ?related  LIMIT " + pageable.getPageSize() + "  OFFSET "
					+ pageable.getOffset();
		}

		return query;
	}

	public static String buildSubquery(String subqueryInfo, String uri, String[] properties, String[] invalidEntities) {
		String[] relations = subqueryInfo.split("\\|");
		String subquery = "{";

		int relationOrder = 0;
		for (String relation : relations) {
			boolean last = (relationOrder + 1) == relations.length;
			boolean first = relationOrder == 0;
			String[] relationData = relation.split(":");
			if (relationData[1].startsWith("back")) {
				subquery += " ?related" + (relationOrder + 1) + (relationData[1].equals("back") ? " asio-def:" : " ")
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

		subquery += "}";

		return subquery;
	}

	private static List<String> findRelations(final String uri, final String type,
			LdpEntitiesProperties ldpEntitiesProperties) {
		List<String> relations = null;
		for (LdpEntityProperties entityProperties : ldpEntitiesProperties.getEntities()) {
			if (Arrays.stream(entityProperties.getType().split(","))
					.anyMatch(entityType -> uri.contains(entityType.trim()))) {
				relations = type.equals("back") ? entityProperties.getBack() : entityProperties.getForward();
				break;
			}
		}

		if (relations == null) {
			relations = Collections.emptyList();
		}
		return relations;
	}

	private static List<String> buildDirectRelation(String type) {
		List<String> directRelation = new ArrayList<String>();
		directRelation.add(type.equals("back") ? "?link:back-direct" : "?link:forward-direct");
		return directRelation;
	}
}
