/*
 *
 */
package es.um.asio.service.service.sparql.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

import es.um.asio.service.domain.SparqlQuery;
import es.um.asio.service.filter.sparql.SparqlQueryFilter;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.model.PageableQuery;
import es.um.asio.service.model.SimpleQuery;
import es.um.asio.service.repository.SparqlQueryRepository;
import es.um.asio.service.service.sparql.QueryBuilder;
import es.um.asio.service.service.sparql.SparqlExecQuery;
import es.um.asio.service.util.FusekiConstants;

/**
 * The Class SparqlExecQueryImpl.
 */
@Service
public class SparqlExecQueryImpl implements SparqlExecQuery {

	private final Logger logger = LoggerFactory.getLogger(SparqlExecQueryImpl.class);

	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;

	@Value("${app.federationAll.url}")
	private String federationUrl;

	@Value("${app.federationNode.url}")
	private String federationNode;

	@Value("${app.serviceDiscoveryExcludeNodes.url}")
	private String serviceDiscoveryExcludeNodes;

	@Value("${app.node}")
	private String node;

	@Value("${app.federation.services}")
	private Boolean federationServices;

	private static final String NODES = "um";

	private static final Integer PAGE_SIZE = 50000;

	@Autowired(required = true)
	private SparqlQueryRepository sparqlQueryRepository;

	@Autowired
	private QueryBuilder queryBuilder;

	@Autowired
	private SqparqlResponseDateFormatter dateFormatter;

	@Autowired
	private ObjectMapper objectMapper;

	/** The rest template. */
	private final RestTemplate restTemplate;

	/** The mapper. */
	private final ObjectMapper mapper;

	/**
	 * Instantiates a new sparql exec query impl.
	 */
	public SparqlExecQueryImpl() {
		this.restTemplate = new RestTemplate();
		this.mapper = new ObjectMapper();
	}

	/**
	 * Method in order to run the query against Fuseki-Trellis
	 *
	 * @param page the page
	 * @return the page
	 */
	@Override
	public Page<FusekiResponse> run(final PageableQuery page) {

		Page<FusekiResponse> result = null;
		List<FusekiResponse> contentResult = new ArrayList<>();
		Integer totalElements = 0;

		this.logger.info("Calling fuseki: {}", this.fusekiTrellisUrl);

		try {
			// we retrieve the params in order to build the query later
			final Map<String, String> params = this.queryBuilder.queryChunks(page.getEntity(), page.getPage());
			params.put(FusekiConstants.FILTERS_CHUNK, page.getFilters());

			this.logger.info("Calling query: {}", this.selectPaginatedQuery(params));

			contentResult = this.getElements(this.selectPaginatedQuery(params));
			totalElements = this.getTotalElements(this.countQuery(params));

			this.logger.info("Total: {}", totalElements);
		} catch (final Exception e) {
			this.logger.error("Error building the page {}", page);
		}

		result = new PageImpl<>(contentResult, page.getPage(), totalElements);

		return result;
	}

	@Override
	public Page<FusekiResponse> runDistinct(final PageableQuery page) {

		Page<FusekiResponse> result = null;
		List<FusekiResponse> contentResult = new ArrayList<>();
		Integer totalElements = 0;

		this.logger.info("Calling fuseki: {}", this.fusekiTrellisUrl);

		try {
			// we retrieve the params in order to build the query later
			final Map<String, String> params = this.queryBuilder.queryChunks(page.getEntity(), page.getPage());
			params.put(FusekiConstants.FILTERS_CHUNK, page.getFilters());

			this.logger.info("Calling query: {}", this.selectPaginatedQueryDistinct(params));

			contentResult = this.getElements(this.selectPaginatedQueryDistinct(params));
			totalElements = this.getTotalElements(this.countQueryDistinct(params));

			this.logger.info("Total: {}", totalElements);
		} catch (final Exception e) {
			this.logger.error("Error building the page {}", page);
		}

		result = new PageImpl<>(contentResult, page.getPage(), totalElements);

		return result;
	}

	@Override
	public List<Object> run(final SimpleQuery query) {

		List<FusekiResponse> contentResult = new ArrayList<>();

		try {
			// we retrieve the params in order to build the query later
			final Map<String, String> params = this.queryBuilder.queryChunks(query.getEntity());
			params.put(FusekiConstants.FILTERS_CHUNK, query.getFilters());

			contentResult = this.getElements(this.selectSimpleQuery(params));
			this.logger.info("Calling query: {}", this.selectSimpleQuery(params));
		} catch (final Exception e) {
			this.logger.error("Error building the page {}", query);
		}

		FusekiResponse fusekiResponse = (contentResult.size() > 0) ? contentResult.get(0) : new FusekiResponse();

		Map<String, Object> mapResults = (Map<String, Object>) fusekiResponse.getResults();

		return (List<Object>) mapResults.get("bindings");
	}

	@Override
	public List<Object> runCount(final SimpleQuery query) {

		List<FusekiResponse> contentResult = new ArrayList<>();

		try {
			// we retrieve the params in order to build the query later
			final Map<String, String> params = this.queryBuilder.queryChunks(query.getEntity());
			params.put(FusekiConstants.FILTERS_CHUNK, query.getFilters());

			contentResult = this.getElements(this.selectSimpleCountQuery(params));
			this.logger.info("Calling query: {}", this.selectSimpleCountQuery(params));
		} catch (final Exception e) {
			this.logger.error("Error building the page {}", query);
		}

		FusekiResponse fusekiResponse = (contentResult.size() > 0) ? contentResult.get(0) : new FusekiResponse();

		Map<String, Object> mapResults = (Map<String, Object>) fusekiResponse.getResults();

		return (List<Object>) mapResults.get("bindings");
	}

	/**
	 * Select query.
	 *
	 * @param params the params
	 * @return the string
	 */
	private String selectPaginatedQuery(final Map<String, String> params) {
		final String result = String.format(FusekiConstants.QUERY_TEMPLATE_SELECT,
				params.get(FusekiConstants.SELECT_CHUNK), params.get(FusekiConstants.TYPE_CHUNK),
				params.get(FusekiConstants.FIELDS_CHUNK), params.get(FusekiConstants.JOIN_CHUNK),
				params.get(FusekiConstants.FILTERS_CHUNK), params.get(FusekiConstants.GROUP),
				params.get(FusekiConstants.ORDER), params.get(FusekiConstants.LIMIT),
				params.get(FusekiConstants.OFFSET));

		return result;
	}

	private String selectPaginatedQueryDistinct(final Map<String, String> params) {
		final String result = String.format(FusekiConstants.QUERY_TEMPLATE_SELECT_DISTINCT,
				params.get(FusekiConstants.SELECT_CHUNK), params.get(FusekiConstants.TYPE_CHUNK),
				params.get(FusekiConstants.FIELDS_CHUNK), params.get(FusekiConstants.JOIN_CHUNK),
				params.get(FusekiConstants.FILTERS_CHUNK), params.get(FusekiConstants.GROUP),
				params.get(FusekiConstants.ORDER), params.get(FusekiConstants.LIMIT),
				params.get(FusekiConstants.OFFSET));

		return result;
	}

	private String selectSimpleQuery(final Map<String, String> params) {
		final String result = String.format(FusekiConstants.QUERY__SIMPLE_TEMPLATE_SELECT,
				params.get(FusekiConstants.SELECT_CHUNK), params.get(FusekiConstants.TYPE_CHUNK),
				params.get(FusekiConstants.FIELDS_CHUNK), params.get(FusekiConstants.JOIN_CHUNK),
				params.get(FusekiConstants.FILTERS_CHUNK), params.get(FusekiConstants.ORDER));

		return result;
	}

	private String selectSimpleCountQuery(final Map<String, String> params) {
		final String result = String.format(FusekiConstants.QUERY__SIMPLE_COUNT_TEMPLATE_SELECT,
				params.get(FusekiConstants.SELECT_CHUNK), params.get(FusekiConstants.COUNT_CHUNK),
				params.get(FusekiConstants.TYPE_CHUNK), params.get(FusekiConstants.FIELDS_CHUNK),
				params.get(FusekiConstants.JOIN_CHUNK), params.get(FusekiConstants.FILTERS_CHUNK),
				params.get(FusekiConstants.GROUP));

		return result;
	}

	/**
	 * Count query.
	 *
	 * @param params the params
	 * @return the string
	 */
	private String countQuery(final Map<String, String> params) {
		final String result = String.format(FusekiConstants.QUERY_TEMPLATE_COUNT,
				params.get(FusekiConstants.COUNT_CHUNK), params.get(FusekiConstants.TYPE_CHUNK),
				params.get(FusekiConstants.FIELDS_CHUNK), params.get(FusekiConstants.JOIN_CHUNK),
				params.get(FusekiConstants.FILTERS_CHUNK), params.get(FusekiConstants.LIMIT),
				params.get(FusekiConstants.OFFSET));

		return result;
	}

	private String countQueryDistinct(final Map<String, String> params) {
		final String result = String.format(FusekiConstants.QUERY_TEMPLATE_COUNT_DISTINCT,
				params.get(FusekiConstants.COUNT_CHUNK), params.get(FusekiConstants.TYPE_CHUNK),
				params.get(FusekiConstants.FIELDS_CHUNK), params.get(FusekiConstants.JOIN_CHUNK),
				params.get(FusekiConstants.FILTERS_CHUNK), params.get(FusekiConstants.LIMIT),
				params.get(FusekiConstants.OFFSET));

		return result;
	}

	/**
	 * Gets the elements.
	 *
	 * @param query the query
	 * @return the elements
	 * @throws JsonProcessingException the json processing exception
	 */
	private List<FusekiResponse> getElements(final String query) throws JsonProcessingException {
		final List<FusekiResponse> result = new ArrayList<>();

		// call for Fuseki-Trellis
		final ResponseEntity<Object> res = this.callFusekiTrellis(query, false);

		if ((res != null) && (res.getBody() != null)) {
			final LinkedHashMap<String, Object> body = (LinkedHashMap<String, Object>) res.getBody();
			if (body != null) {
				FusekiResponse fusekiResponse = new FusekiResponse();
				fusekiResponse.setHead(body.get("head"));
				fusekiResponse.setResults(body.get("results"));

				result.add(fusekiResponse);
			}
		}

		return result;
	}

	private Integer getTotalElements(final String query) {
		Integer result = 0;

		// call for Fuseki-Trellis
		final ResponseEntity<Object> res = this.callFusekiTrellis(query, false);

		if ((res != null) && (res.getBody() != null)) {
			final LinkedHashMap<String, Object> body = (LinkedHashMap<String, Object>) res.getBody();
			if (body != null) {
				// ugly hierarchy in Fuseki response
				final LinkedHashMap<String, Object> results = (LinkedHashMap<String, Object>) body.get("results");
				final ArrayList bindingArray = (ArrayList) results.get("bindings");
				final LinkedHashMap<String, Object> bindingElement = (LinkedHashMap<String, Object>) bindingArray
						.get(0);
				final LinkedHashMap<String, Object> countElement = (LinkedHashMap<String, Object>) bindingElement
						.get("count");
				final String value = (String) countElement.get("value");
				result = Integer.valueOf(value);
			}

		}

		return result;
	}

	@Override
	public ResponseEntity<Object> callFusekiTrellis(final String query, Boolean isFederated) {
		return callFusekiTrellis(query, isFederated, null);
	}

	/**
	 * Call fuseki trellis.
	 *
	 * @param query the query
	 * @return the response entity
	 */
	@Override
	public ResponseEntity<Object> callFusekiTrellis(final String query, Boolean isFederated, String accept) {
		ResponseEntity<Object> result = null;
		if (!federationServices) {
			try {
				HttpEntity<MultiValueMap<String, String>> body = this.getBody(query);
				if (accept != null) {
					HttpHeaders headers = this.getHeaders();
					headers.setAccept(Arrays.asList(MediaType.valueOf(accept)));
					body = this.getBody(query, headers);
				}
				result = this.restTemplate.exchange(this.fusekiTrellisUrl, HttpMethod.POST, body, Object.class);
				result = formatResponse(result);
			} catch (final Exception e) {
				this.logger.error("Error retrieving results from fuseki cause {}", e.getMessage());
			}
		} else {
			ResponseEntity<Object> tempResult;

			if (!isFederated) {
				try {
					tempResult = this.restTemplate.exchange(this.federationNode, HttpMethod.POST,
							this.getBody(query, PAGE_SIZE, "fuseki", NODES), Object.class);

					result = createResult(tempResult);
				} catch (final Exception e) {
					this.logger.error("Error retrieving results from fuseki cause {}", e.getMessage());
				}
			} else {

				try {
					tempResult = this.restTemplate.exchange(this.serviceDiscoveryExcludeNodes + "?nodeName=" + node,
							HttpMethod.GET, getBodyServiceDiscovery(node), Object.class);
					String nodes;
					if (tempResult.getStatusCode().is2xxSuccessful()) {
						nodes = String.join(",", (List<String>) createResult(tempResult).getBody());
					} else {
						nodes = node;
					}
					tempResult = this.restTemplate.exchange(this.federationNode, HttpMethod.POST,
							this.getBody(query, PAGE_SIZE, "fuseki", nodes), Object.class);

					result = createResult(tempResult);

				} catch (final Exception e) {
					this.logger.error("Error retrieving results from federation cause {}", e.getMessage());
				}
			}

		}
		return result;
	}

	private ResponseEntity<Object> createResult(ResponseEntity<Object> tempResult) {
		ResponseEntity<Object> result;
		MultiValueMap<String, String> newHeader = new LinkedMultiValueMap<>();
		newHeader.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		tempResult = formatResponse(tempResult);
		return new ResponseEntity<Object>(tempResult.getBody(), newHeader, tempResult.getStatusCode());
	}

	/**
	 * Gets the headers.
	 *
	 * @return the headers
	 */
	private HttpHeaders getHeaders() {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		return headers;
	}

	private HttpEntity<MultiValueMap<String, String>> getBody(final String query) {
		return getBody(query, this.getHeaders());
	}

	/**
	 * Gets the body.
	 *
	 * @param query the query
	 * @return the body
	 */
	private HttpEntity<MultiValueMap<String, String>> getBody(final String query, final HttpHeaders headers) {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("query", query);

		final HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);

		return body;
	}

	private HttpEntity<MultiValueMap<String, String>> getBody(final String query, final int pageSize,
			final String tripleStore) {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("query", query);
		params.add("pageSize", Integer.toString(pageSize));
		params.add("tripleStore", tripleStore);

		final HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, this.getHeaders());

		return body;
	}

	private HttpEntity<MultiValueMap<String, String>> getBody(final String query, final int pageSize,
			final String tripleStore, final List<String> nodeList) {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("query", query);
		params.add("pageSize", Integer.toString(pageSize));
		params.add("tripleStore", tripleStore);
		params.add("nodeList", String.join(", ", nodeList));

		final HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, this.getHeaders());

		return body;
	}

	private HttpEntity<MultiValueMap<String, String>> getBody(final String query, final int pageSize,
			final String tripleStore, final String nodes) {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("query", query);
		params.add("pageSize", Integer.toString(pageSize));
		params.add("tripleStore", tripleStore);
		params.add("nodeList", nodes);

		final HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, this.getHeaders());

		return body;
	}

	private HttpEntity<MultiValueMap<String, String>> getBodyServiceDiscovery(final String node) {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("nodeName", node);
		final HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, this.getHeaders());

		return body;
	}

	@Override
	public Page<SparqlQuery> findPaginated(SparqlQueryFilter filter, Pageable pageable) {
		return this.sparqlQueryRepository.findAll(filter, pageable);
	}

	@Override
	public SparqlQuery save(SparqlQuery sparqlQuery) {
		return this.sparqlQueryRepository.saveAndFlush(sparqlQuery);
	}

	@Override
	public SparqlQuery update(SparqlQuery sparqlQuery) {
		return this.sparqlQueryRepository.saveAndFlush(sparqlQuery);
	}

	@Override
	public void delete(String id) {
		this.sparqlQueryRepository.deleteById(id);
	}

	private ResponseEntity<Object> formatResponse(ResponseEntity<Object> response) {
		if (response == null || response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
			return response;
		}

		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject((LinkedHashMap<String, Object>) response.getBody());
			DocumentContext parsedDataContext = JsonPath.parse(jsonObject.toString());
			parsedDataContext.map("$.results.bindings[*].*.value", dateFormatter);

			return new ResponseEntity<Object>(
					objectMapper.readValue(parsedDataContext.jsonString(), LinkedHashMap.class), response.getHeaders(),
					response.getStatusCode());
		} catch (PathNotFoundException p) {
			this.logger.debug(String.format("formatResponse - Invalid path for response. response: %",
					jsonObject != null ? jsonObject.toString() : null));
		} catch (Exception e) {
			this.logger.error(String.format("formatResponse - Unknown error formatting response. response: %s",
					jsonObject != null ? jsonObject.toString() : null), e);
		}

		return response;

	}

	@Override
	public Page<FusekiResponse> runOrganization(PageableQuery page) {
		Page<FusekiResponse> result = null;
		List<FusekiResponse> contentResult = new ArrayList<>();
		Integer totalElements = 0;

		this.logger.info("Calling fuseki: {}", this.fusekiTrellisUrl);

		try {
			// we retrieve the params in order to build the query later
			final Map<String, String> params = this.queryBuilder.queryChunksOrganization(page.getEntity(),
					page.getPage());
			params.put(FusekiConstants.FILTERS_CHUNK, page.getFilters());

			this.logger.info("Calling query: {}", this.selectPaginatedQueryDistinct(params));

			contentResult = this.getElements(this.selectPaginatedQueryDistinct(params));
			totalElements = this.getTotalElements(this.countQueryDistinct(params));

			this.logger.info("Total: {}", totalElements);
		} catch (final Exception e) {
			this.logger.error("Error building the page {}", page);
		}

		result = new PageImpl<>(contentResult, page.getPage(), totalElements);

		return result;
	}

}
