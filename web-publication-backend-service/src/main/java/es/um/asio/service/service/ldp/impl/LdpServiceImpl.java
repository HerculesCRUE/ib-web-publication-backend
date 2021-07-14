package es.um.asio.service.service.ldp.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.LdpEntityCountDto;
import es.um.asio.service.dto.LdpSearchResultDto;
import es.um.asio.service.service.ldp.LdpService;
import es.um.asio.service.service.sparql.SparqlExecQuery;

@Service
public class LdpServiceImpl implements LdpService {

	
	private static final String COUNT_QUERY = "SELECT ?entity (count(distinct ?ac) as ?count) " + "WHERE { "
			+ "  ?ac <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?entity . "
			+ "  FILTER regex(str(?entity), \"%s\", \"i\") " + "} " + "GROUP BY ?entity " + "ORDER BY %s (?%s) "
			+ "LIMIT %s " + "OFFSET %s ";

	private static final String COUNT_QUERY_COUNT = "SELECT (count(distinct ?entity) as ?count) " + "WHERE { "
			+ "  ?ac <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?entity . "
			+ "  FILTER regex(str(?entity), \"%s\", \"i\") " + "}";
	
	private static final String TITLE_QUERY = "SELECT ?uri ?title "
			+ "WHERE {"
			+ "{?uri <%s/title> ?title."
			+ "    FILTER regex(?title, \"%s\", \"i\")} "
			+ "UNION "
			+ "{ ?uri <%s/name> ?title."
			+ "    FILTER regex(?title, \"%s\", \"i\") }"
			+ "} "
			+ "ORDER BY %s (?%s) "
			+ "LIMIT %s " + "OFFSET %s ";
	
	private static final String TITLE_QUERY_COUNT = "SELECT (count(?uri) as ?count) "
			+ "WHERE {"
			+ "{?uri <%s/title> ?title."
			+ "    FILTER regex(?title, \"%s\", \"i\")} "
			+ "UNION "
			+ "{ ?uri <%s/name> ?title."
			+ "    FILTER regex(?title, \"%s\", \"i\") }"
			+ "} ";

	private final Logger logger = LoggerFactory.getLogger(LdpServiceImpl.class);

	@Value("${app.ldp.uri-namespace}")
	private String uriNamespace;

	@Autowired
	private SparqlExecQuery sparqlExecQuery;

	@Override
	public Page<LdpEntityCountDto> entityCount(final Pageable pageable) {
		return new PageImpl<LdpEntityCountDto>(getCountElements(pageable), pageable,
				getElementsCount(String.format(COUNT_QUERY_COUNT, uriNamespace)));
	}
	
	@Override
	public Page<LdpSearchResultDto> findByTitle(final String title, final Pageable pageable) {
		return new PageImpl<LdpSearchResultDto>(findElements(title, pageable), pageable,
				getElementsCount(String.format(TITLE_QUERY_COUNT, uriNamespace, title, uriNamespace, title)));
	}

	private String buildQuery(String query, Pageable pageable) {
		Order order = pageable.getSort().toList().get(0);
		return String.format(query, uriNamespace, order.getDirection(), order.getProperty(), pageable.getPageSize(),
				pageable.getOffset());
	}

	private List<LdpEntityCountDto> getCountElements(final Pageable pageable) {
		List<LdpEntityCountDto> ldpEntityCountDtos = new ArrayList<LdpEntityCountDto>();
		String query = buildQuery(COUNT_QUERY, pageable);
		logger.info(String.format("getCountElements - Executing query %s", query));
		ResponseEntity<Object> response = sparqlExecQuery.callFusekiTrellis(query, false);

		try {
			if (response.getStatusCode() == HttpStatus.OK) {
				JSONObject jsonObject = new JSONObject((LinkedHashMap<String, Object>) response.getBody());
				JSONArray jsonResults = jsonObject.getJSONObject("results").getJSONArray("bindings");

				for (int i = 0; i < jsonResults.length(); i++) {
					JSONObject jsonResult = jsonResults.getJSONObject(i);
					LdpEntityCountDto entityCountDto = new LdpEntityCountDto();
					entityCountDto.setEntity(jsonResult.getJSONObject("entity").getString("value"));
					entityCountDto.setCount(Integer.parseInt(jsonResult.getJSONObject("count").getString("value")));
					ldpEntityCountDtos.add(entityCountDto);
				}
			} else {
				logger.error(String.format("getCountElements - Response error. code: %s | query: %s ",
						response.getStatusCode().name(), query));
			}
		} catch (Exception e) {
			logger.error(String.format("getCountElements - Unkown error. query: %s ", query), e);
		}
		return ldpEntityCountDtos;
	}
	
	private String buildFindQuery(String query, String title, Pageable pageable) {
		Order order = pageable.getSort().toList().get(0);
		return String.format(query, uriNamespace, title, uriNamespace, title, order.getDirection(), order.getProperty(), pageable.getPageSize(),
				pageable.getOffset());
	}
	
	private List<LdpSearchResultDto> findElements(final String title, final Pageable pageable) {
		List<LdpSearchResultDto> ldpSearchResultDtos = new ArrayList<LdpSearchResultDto>();
		String query = buildFindQuery(TITLE_QUERY, title, pageable);
		logger.info(String.format("findElements - Executing query %s", query));
		ResponseEntity<Object> response = sparqlExecQuery.callFusekiTrellis(query, false);

		try {
			if (response.getStatusCode() == HttpStatus.OK) {
				JSONObject jsonObject = new JSONObject((LinkedHashMap<String, Object>) response.getBody());
				JSONArray jsonResults = jsonObject.getJSONObject("results").getJSONArray("bindings");

				for (int i = 0; i < jsonResults.length(); i++) {
					JSONObject jsonResult = jsonResults.getJSONObject(i);
					LdpSearchResultDto entityCountDto = new LdpSearchResultDto();
					entityCountDto.setUri(jsonResult.getJSONObject("uri").getString("value"));
					entityCountDto.setTitle(jsonResult.getJSONObject("title").getString("value"));
					ldpSearchResultDtos.add(entityCountDto);
				}
			} else {
				logger.error(String.format("findElements - Response error. code: %s | query: %s ",
						response.getStatusCode().name(), query));
			}
		} catch (Exception e) {
			logger.error(String.format("findElements - Unkown error. query: %s ", query), e);
		}
		return ldpSearchResultDtos;
	}

	private Integer getElementsCount(final String countQuery) {
		Integer count = 0;		
		logger.info(String.format("getElementsCount - Executing query %s", countQuery));
		ResponseEntity<Object> response = sparqlExecQuery.callFusekiTrellis(countQuery, false);

		try {
			if (response.getStatusCode() == HttpStatus.OK) {
				JSONObject jsonObject = new JSONObject((LinkedHashMap<String, Object>) response.getBody());
				count = Integer.parseInt(jsonObject.getJSONObject("results").getJSONArray("bindings").getJSONObject(0)
						.getJSONObject("count").getString("value"));
				logger.info(String.format("getElementsCount - Count %s", count));
			} else {
				logger.error(String.format("getElementsCount - Response error. code: %s | query: %s ",
						response.getStatusCode().name(), countQuery));
			}
		} catch (Exception e) {
			logger.error(String.format("getElementsCount - Unkown error. query: %s ", countQuery), e);
		}
		return count;
	}

}
