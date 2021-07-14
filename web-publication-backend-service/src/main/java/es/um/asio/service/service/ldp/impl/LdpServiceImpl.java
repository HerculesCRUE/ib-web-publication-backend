package es.um.asio.service.service.ldp.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.LdpEntityCountDto;
import es.um.asio.service.service.ldp.LdpService;
import es.um.asio.service.service.sparql.SparqlExecQuery;

@Service
public class LdpServiceImpl implements LdpService {
	
	private static final String COUNT_QUERY = "SELECT ?entity (count(distinct ?ac) as ?count) "
			+ "WHERE { "
			+ "  ?ac <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?entity . "
			+ "  FILTER regex(str(?entity), \"http://hercules.org/um/es-ES/rec\", \"i\") "
			+ "} "
			+ "GROUP BY ?entity "
			+ "ORDER BY %s (?%s) "
			+ "LIMIT %s "
			+ "OFFSET %s ";
	
	private static final String COUNT_QUERY_COUNT = "SELECT (count(distinct ?entity) as ?count) "
			+ "WHERE { "
			+ "  ?ac <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?entity . "
			+ "  FILTER regex(str(?entity), \"http://hercules.org/um/es-ES/rec\", \"i\") "
			+ "}";
	
	@Autowired
	private SparqlExecQuery sparqlExecQuery;

	@Override
	public Page<LdpEntityCountDto> entityCount(final Pageable pageable) {	
		return new PageImpl<LdpEntityCountDto>(getCountElements(pageable), pageable, getElementsCount(COUNT_QUERY_COUNT));
	}
	
	private String buildQuery(String query, Pageable pageable) {
		Order order = pageable.getSort().toList().get(0);
		return String.format(query, order.getDirection(), order.getProperty(), pageable.getPageSize(), pageable.getOffset());
	}
	
	private List<LdpEntityCountDto> getCountElements(final Pageable pageable) {
		List<LdpEntityCountDto> ldpEntityCountDtos = new ArrayList<LdpEntityCountDto>();
		ResponseEntity<Object> response = sparqlExecQuery.callFusekiTrellis(buildQuery(COUNT_QUERY, pageable), false);
	
		try {
			if (response.getStatusCode() == HttpStatus.OK) {
				JSONObject jsonObject = new JSONObject((LinkedHashMap<String, Object>)response.getBody());
				JSONArray jsonResults = jsonObject.getJSONObject("results").getJSONArray("bindings"); 
				
				for (int i = 0; i < jsonResults.length(); i++) {
					JSONObject jsonResult = jsonResults.getJSONObject(i);
					LdpEntityCountDto entityCountDto = new LdpEntityCountDto();
					entityCountDto.setEntity(jsonResult.getJSONObject("entity").getString("value"));
					entityCountDto.setCount(Integer.parseInt(jsonResult.getJSONObject("count").getString("value")));
					ldpEntityCountDtos.add(entityCountDto);
				}				
			}
		}catch (Exception e) {
		}
		return ldpEntityCountDtos;
	}
	
	private Integer getElementsCount(final String countQuery) {
		Integer count = 0;
		ResponseEntity<Object> response = sparqlExecQuery.callFusekiTrellis(countQuery, false);
		
		try {
			if (response.getStatusCode() == HttpStatus.OK) {
				JSONObject jsonObject = new JSONObject((LinkedHashMap<String, Object>)response.getBody());
				count = Integer.parseInt(jsonObject.getJSONObject("results").getJSONArray("bindings").getJSONObject(0).getJSONObject("count").getString("value"));										
			}
		}catch (Exception e) {
		}
		return count;
	}

}
