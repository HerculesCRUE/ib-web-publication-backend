package es.um.asio.service.service.ldp.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import es.um.asio.service.config.properties.LdpEntitiesProperties;
import es.um.asio.service.dto.LdpEntityCountDto;
import es.um.asio.service.dto.LdpEntityDetailsDto;
import es.um.asio.service.dto.LdpEntityDetailsDto.LdpEntityDetail;
import es.um.asio.service.dto.LdpEntityRelatedDto;
import es.um.asio.service.dto.LdpSearchResultDto;
import es.um.asio.service.service.ldp.LdpService;
import es.um.asio.service.service.ldp.impl.LdpQueryUtils.Category;
import es.um.asio.service.service.ldp.impl.LdpQueryUtils.Type;
import es.um.asio.service.service.sparql.SparqlExecQuery;

@Service
public class LdpServiceImpl implements LdpService {

	private static final String TITLE_QUERY_TYPE_TITLE = "titleFilter";
	private static final String TITLE_QUERY_TYPE_CATEGORY = "uri";

	private static final String COUNT_QUERY = "SELECT ?entity (count(distinct ?ac) as ?count) " + "WHERE { "
			+ "  ?ac <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?entity . "
			+ "  FILTER regex(str(?entity), \"%s/rec\", \"i\") " + "} " + "GROUP BY ?entity " + "ORDER BY %s (?%s) "
			+ "LIMIT %s " + "OFFSET %s ";

	private static final String COUNT_QUERY_COUNT = "SELECT (count(distinct ?entity) as ?count) " + "WHERE { "
			+ "  ?ac <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?entity . "
			+ "  FILTER regex(str(?entity), \"%s/rec\", \"i\") " + "}";

	private static final String TITLE_QUERY = "SELECT ?uri (GROUP_CONCAT(?titleFilter; SEPARATOR=\", \") AS ?title) "
			+ "WHERE {" + "{?uri <%s/def/title> ?titleFilter." + "    FILTER regex(str(?%s), \"%s\", \"i\")} "
			+ "UNION " + "{ ?uri <%s/def/name> ?titleFilter." + "    FILTER regex(str(?%s), \"%s\", \"i\") } "
			+ "UNION { ?uri <%s/def/correspondingAuthor> ?titleFilter." + "    FILTER regex(str(?%s), \"%s\", \"i\") } UNION "
			+ "{ ?uri <%s/def/id> ?titleFilter." + "    FILTER regex(str(?%s), \"%s\", \"i\") }" + "} "
			+ "GROUP BY ?uri " + "ORDER BY %s (?%s) " + "LIMIT %s " + "OFFSET %s ";

	private static final String TITLE_QUERY_COUNT = "SELECT (count(distinct ?uri) as ?count) " + "WHERE {"
			+ "{?uri <%s/def/title> ?titleFilter." + "    FILTER regex(str(?%s), \"%s\", \"i\")} " + "UNION "
			+ "{ ?uri <%s/def/name> ?titleFilter." + "    FILTER regex(str(?%s), \"%s\", \"i\") }" + "UNION "
			+ "{ ?uri <%s/def/correspondingAuthor> ?titleFilter." + "    FILTER regex(str(?%s), \"%s\", \"i\") }" + "UNION "
			+ "{ ?uri <%s/def/id> ?titleFilter." + "    FILTER regex(str(?%s), \"%s\", \"i\") }" + "} ";

	private static final String ENTITY_QUERY = "SELECT * WHERE " + " { <%s> ?key ?value. }";

	private static final String JSON_LD_TEMPLATE = "CONSTRUCT {?ac ?key ?value} WHERE {%s}";

	private final Logger logger = LoggerFactory.getLogger(LdpServiceImpl.class);

	@Autowired
	private SparqlExecQuery sparqlExecQuery;

	@Autowired
	private LdpEntitiesProperties ldpEntitiesProperties;

	@Override
	public Page<LdpEntityCountDto> entityCount(final Pageable pageable) {
		List<LdpEntityCountDto> results = executeQuery(buildQuery(COUNT_QUERY, pageable), this::mapToLdpEntityCountDto);
		results = results.stream().filter(count -> ldpEntitiesProperties.isValidCategory(count.getEntity())).collect(Collectors.toList());
		Integer count = executeQuery(String.format(COUNT_QUERY_COUNT, ldpEntitiesProperties.getUriNamespace()),
				this::mapToCount).get(0);
		return new PageImpl<LdpEntityCountDto>(results, pageable, count);
	}

	@Override
	public Page<LdpSearchResultDto> findByTitleOrName(final String title, final Pageable pageable) {
		return findByTitle(title, TITLE_QUERY_TYPE_TITLE, pageable);
	}

	@Override
	public Page<LdpSearchResultDto> findByCategory(final String category, final Pageable pageable) {
		// Query uses like filter. For http://hercules.org/um/es-ES/rec/Book it also
		// retrieves http://hercules.org/um/es-ES/rec/Book-chapter. A '/' character is
		// added to the end of category
		return findByTitle(category.concat("/"), TITLE_QUERY_TYPE_CATEGORY, pageable);
	}

	private Page<LdpSearchResultDto> findByTitle(final String searchToken, final String type, final Pageable pageable) {
		List<LdpSearchResultDto> results = executeQuery(buildFindQuery(TITLE_QUERY, type, searchToken, pageable),
				this::mapToLdpSearchResultDto);
		results = results.stream().filter(count -> ldpEntitiesProperties.isValidEntity(count.getUri())).collect(Collectors.toList());
		Integer count = executeQuery(String.format(TITLE_QUERY_COUNT, ldpEntitiesProperties.getUriNamespace(), type,
				searchToken, ldpEntitiesProperties.getUriNamespace(), type, searchToken,
				ldpEntitiesProperties.getUriNamespace(), type, searchToken,
				ldpEntitiesProperties.getUriNamespace(), type, searchToken), this::mapToCount).get(0);
		return new PageImpl<LdpSearchResultDto>(results, pageable, count);
	}

	@Override
	public LdpEntityDetailsDto findDetails(final String uri) {
		LdpEntityDetailsDto detailsDto = new LdpEntityDetailsDto();
		detailsDto.setProperties(executeQuery(String.format(ENTITY_QUERY, uri), this::mapToLdpEntityDetailsDto));
		detailsDto.setUri(uri);
		detailsDto.setJsonLd(executeQuery(String.format(JSON_LD_TEMPLATE, String.format(ENTITY_QUERY, uri)), this::mapToString, false,
				"application/ld+json").get(0));
		return detailsDto;
	}

	@Override
	public Page<LdpEntityRelatedDto> findRelated(final String uri, final Pageable pageable, final String type) {
		Category category = Category.from(type);
		
		String findRelatedQuery = LdpQueryUtils.buildRelatedQuery(ldpEntitiesProperties, uri, pageable, Type.DATA, category);

		if (StringUtils.isBlank(findRelatedQuery)) {
			return new PageImpl<LdpEntityRelatedDto>(Collections.emptyList(), pageable, 0);
		}

		List<LdpEntityRelatedDto> results = executeQuery(findRelatedQuery, this::mapToLdpEntityRelatedDto);

		String findCountRelatedQuery = LdpQueryUtils.buildRelatedQuery(ldpEntitiesProperties, uri, pageable, Type.COUNT,
				category);
		Integer count = executeQuery(findCountRelatedQuery, this::mapToCount).get(0);
		return new PageImpl<LdpEntityRelatedDto>(results, pageable, count);
	}
	
	@Override
	public List<String> findRelatedCategories(final String uri, final String type) {		
		String findRelatedQuery = LdpQueryUtils.buildRelatedQuery(ldpEntitiesProperties, uri, null, Type.GROUP, Category.from(type));

		if (StringUtils.isBlank(findRelatedQuery)) {
			return Collections.emptyList();
		}

		return executeQuery(findRelatedQuery, this::mapToGroup).stream()
				.map(categoryGroup -> extractTypeFromUri(ldpEntitiesProperties.getUriNamespace(), categoryGroup))
				.collect(Collectors.toList());		
	}

	private String buildQuery(String query, Pageable pageable) {
		Order order = pageable.getSort().toList().get(0);
		return String.format(query, ldpEntitiesProperties.getUriNamespace(), order.getDirection(), order.getProperty(),
				pageable.getPageSize(), pageable.getOffset());
	}

	private String buildFindQuery(String query, String type, String title, Pageable pageable) {
		Order order = new Order(Direction.ASC, "uri");
		if (!pageable.getSort().toList().isEmpty()) {
			order = pageable.getSort().toList().get(0);
		}
		return String.format(query, ldpEntitiesProperties.getUriNamespace(), type, title,
				ldpEntitiesProperties.getUriNamespace(), type, title, ldpEntitiesProperties.getUriNamespace(), type,
				title, ldpEntitiesProperties.getUriNamespace(), type,
				title, order.getDirection(), order.getProperty(), pageable.getPageSize(), pageable.getOffset());
	}

	private <T> List<T> executeQuery(final String query, final Function<JSONObject, T> mapper) {
		return executeQuery(query, mapper, true, null);
	}

	private <T> List<T> executeQuery(final String query, final Function<JSONObject, T> mapper, final boolean mapResults,
			final String accept) {
		List<T> ldpSearchResultDtos = new ArrayList<T>();
		logger.info(String.format("exceuteQuery - Executing query %s", query));
		ResponseEntity<Object> response = sparqlExecQuery.callFusekiTrellis(query, false, accept);

		try {
			if (response.getStatusCode() == HttpStatus.OK) {

				JSONObject jsonObject = new JSONObject((LinkedHashMap<String, Object>) response.getBody());

				if (!mapResults) {
					ldpSearchResultDtos.add(mapper.apply(jsonObject));
					return ldpSearchResultDtos;
				}

				JSONArray jsonResults = jsonObject.getJSONObject("results").getJSONArray("bindings");

				for (int i = 0; i < jsonResults.length(); i++) {
					JSONObject jsonResult = jsonResults.getJSONObject(i);
					T mappedElement = mapper.apply(jsonResult);
					if (mappedElement != null) {
						ldpSearchResultDtos.add(mappedElement);
					}
				}
			} else {
				logger.error(String.format("exceuteQuery - Response error. code: %s | query: %s ",
						response.getStatusCode().name(), query));
			}
		} catch (Exception e) {
			logger.error(String.format("exceuteQuery - Unkown error. query: %s ", query), e);
		}
		return ldpSearchResultDtos;
	}

	private LdpSearchResultDto mapToLdpSearchResultDto(JSONObject jsonObject) {
		LdpSearchResultDto dto = new LdpSearchResultDto();
		try {
			dto.setUri(jsonObject.getJSONObject("uri").getString("value"));
			String title = jsonObject.getJSONObject("title").getString("value");
			if (title != null) {
				title = Stream.of(title.split(",")).filter(titleToken -> !titleToken.isEmpty()).findFirst()
						.orElse(title);
			}
			dto.setTitle(title);

		} catch (Exception e) {
			return null;
		}
		return dto;
	}

	private LdpEntityCountDto mapToLdpEntityCountDto(JSONObject jsonObject) {
		LdpEntityCountDto dto = new LdpEntityCountDto();
		try {
			dto.setEntity(jsonObject.getJSONObject("entity").getString("value"));
			dto.setCount(Integer.parseInt(jsonObject.getJSONObject("count").getString("value")));
		} catch (Exception e) {
			return null;
		}
		return dto;
	}

	private LdpEntityDetail mapToLdpEntityDetailsDto(JSONObject jsonObject) {
		try {
			String key = jsonObject.getJSONObject("key").getString("value");
			String value = jsonObject.getJSONObject("value").getString("value");
			if (extractTypeFromUri(ldpEntitiesProperties.getUriNamespace(), value) != null) {
				return null;
			}
			if(StringUtils.isBlank(value) || value.equals("null")) {
				return null;
			}
			return LdpEntityDetailsDto.buildDetail(key, value);
		} catch (Exception e) {
			return null;
		}
	}

	private LdpEntityRelatedDto mapToLdpEntityRelatedDto(JSONObject jsonObject) {
		LdpEntityRelatedDto ldpEntityRelatedDto = new LdpEntityRelatedDto();
		try {
			String relationship = getValueFromProperty(jsonObject, "link_result");
			String relatedUri = getValueFromProperty(jsonObject, "related");

			String relatedDescription = null;
			for (String property : ldpEntitiesProperties.getValidProperties()) {
				if (!StringUtils.isBlank(getValueFromProperty(jsonObject, property + "_result")) && !getValueFromProperty(jsonObject, property + "_result").equals("null")) {
					relatedDescription = getValueFromProperty(jsonObject, property + "_result");
					break;
				}
			}

			ldpEntityRelatedDto
					.setRelationship(extractRelationShipFromUri(ldpEntitiesProperties.getUriNamespace(), relationship));
			ldpEntityRelatedDto.setRelatedType(extractTypeFromUri(ldpEntitiesProperties.getUriNamespace(), relatedUri));
			ldpEntityRelatedDto.setRelatedUri(relatedUri);
			ldpEntityRelatedDto.setRelatedDescription(relatedDescription);

			return ldpEntityRelatedDto;
		} catch (Exception e) {
			logger.error("mapToLdpEntityRelatedDto - Unkown error. " + jsonObject, e);
			return null;
		}
	}

	private Integer mapToCount(JSONObject jsonObject) {
		try {
			return Integer.parseInt(jsonObject.getJSONObject("count").getString("value"));
		} catch (Exception e) {
			return null;
		}
	}
	
	private String mapToGroup(JSONObject jsonObject) {
		try {
			return jsonObject.getJSONObject("type").getString("value");
		} catch (Exception e) {
			return null;
		}
	}

	private String mapToString(JSONObject jsonObject) {
		try {
			return jsonObject.toString();
		} catch (Exception e) {
			return null;
		}
	}
	
	private String getValueFromProperty(final JSONObject jsonObject, final String property) {
		try {
			return jsonObject.getJSONObject(property).getString("value");
		} catch (Exception e) {
			return null;
		}
	}

	private String extractTypeFromUri(String uriNamespace, String uri) {
		String pattern = "^((" + uriNamespace
				+ "/rec/)([a-z\\-A-Z]+))+(?:/([a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}))?$";
		final Matcher urlRegExpMatcher = Pattern.compile(pattern).matcher(uri);
		if (urlRegExpMatcher.find() && urlRegExpMatcher.groupCount() == 4) {
			return urlRegExpMatcher.group(3);
		}
		return null;
	}

	private String extractRelationShipFromUri(String uriNamespace, String relationShip) {
		String pattern = "(" + uriNamespace + "/def/)(.*)";
		final Matcher urlRegExpMatcher = Pattern.compile(pattern).matcher(relationShip);
		if (urlRegExpMatcher.find() && urlRegExpMatcher.groupCount() == 2) {
			return urlRegExpMatcher.group(2);
		}
		return null;
	}
}
