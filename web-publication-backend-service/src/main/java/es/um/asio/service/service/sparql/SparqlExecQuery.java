package es.um.asio.service.service.sparql;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.model.PageableQuery;

public interface SparqlExecQuery {

	/**
	 * Run.
	 *
	 * @param page the page
	 * @return the page
	 */
	Page<FusekiResponse> run(PageableQuery page);
	
	/**
	 * Call fuseki trellis.
	 *
	 * @param query the query
	 * @return the response entity
	 */
	ResponseEntity<Object> callFusekiTrellis(String query, Boolean isFederated);
	

}
