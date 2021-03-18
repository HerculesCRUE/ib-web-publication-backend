/*
 *
 */
package es.um.asio.service.service.sparql.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.model.PageableQuery;
import es.um.asio.service.model.SimpleQuery;
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

	@Value("${app.federation.services}")
	private Boolean federationServices;

	private static final String NODES = "um";

	private static final Integer PAGE_SIZE = 50000;

	@Autowired
	private QueryBuilder queryBuilder;

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

			contentResult = this.getElements(this.selectPaginatedQuery(params));
			totalElements = this.getTotalElements(this.countQuery(params));
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
		} catch (final Exception e) {
			this.logger.error("Error building the page {}", query);
		}
		
		FusekiResponse fusekiResponse = (contentResult.size() > 0)? contentResult.get(0) : new FusekiResponse();
		
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
	
	private String selectSimpleQuery(final Map<String, String> params) {
		final String result = String.format(FusekiConstants.QUERY__SIMPLE_TEMPLATE_SELECT,
				params.get(FusekiConstants.SELECT_CHUNK), params.get(FusekiConstants.TYPE_CHUNK),
				params.get(FusekiConstants.FIELDS_CHUNK), params.get(FusekiConstants.JOIN_CHUNK),
				params.get(FusekiConstants.FILTERS_CHUNK));

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

	/**
	 * Call fuseki trellis.
	 *
	 * @param query the query
	 * @return the response entity
	 */
	@Override
	public ResponseEntity<Object> callFusekiTrellis(final String query, Boolean isFederated) {
		ResponseEntity<Object> result = null;
		if (!federationServices) {
			try {
				result = this.restTemplate.exchange(this.fusekiTrellisUrl, HttpMethod.POST, this.getBody(query),
						Object.class);
			} catch (final Exception e) {
				this.logger.error("Error retrieving results from fuseki cause {}", e.getMessage());
			}
		} else {
			ResponseEntity<Object> tempResult ;
			
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
					tempResult = this.restTemplate.exchange(this.federationUrl, HttpMethod.POST,
							this.getBody(query, PAGE_SIZE, "fuseki"), Object.class);

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
		return new ResponseEntity<Object>(tempResult.getBody(),newHeader, tempResult.getStatusCode());
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

	/**
	 * Gets the body.
	 *
	 * @param query the query
	 * @return the body
	 */
	private HttpEntity<MultiValueMap<String, String>> getBody(final String query) {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("query", query);

		final HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, this.getHeaders());

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
			final String tripleStore, final String nodes) {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("query", query);
		params.add("pageSize", Integer.toString(pageSize));
		params.add("tripleStore", tripleStore);
		params.add("nodeList", nodes);

		final HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, this.getHeaders());

		return body;
	}

}
