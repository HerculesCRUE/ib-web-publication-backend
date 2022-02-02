package es.um.asio.service.service.sparql;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import es.um.asio.service.domain.SparqlQuery;
import es.um.asio.service.filter.sparql.SparqlQueryFilter;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.model.PageableQuery;
import es.um.asio.service.model.SimpleQuery;

public interface SparqlExecQuery {

	/**
	 * Run.
	 *
	 * @param page the page
	 * @return the page
	 */
	Page<FusekiResponse> run(PageableQuery page);

	Page<FusekiResponse> runDistinct(PageableQuery page);

	/**
	 * Run.
	 *
	 * @param query the query
	 * @return the fuseki response
	 */
	List<Object> run(final SimpleQuery query);

	/**
	 * Call fuseki trellis.
	 *
	 * @param query the query
	 * @return the response entity
	 */
	ResponseEntity<Object> callFusekiTrellis(String query, Boolean isFederated);

	ResponseEntity<Object> callFusekiTrellis(final String query, Boolean isFederated, String accept);

	List<Object> runCount(SimpleQuery query);

	Page<SparqlQuery> findPaginated(SparqlQueryFilter filter, Pageable pageable);

	SparqlQuery save(SparqlQuery sparqlQuery);

	SparqlQuery update(SparqlQuery convertSparqlQueryFromDto);

	void delete(String id);

	Page<FusekiResponse> runOrganization(PageableQuery pageableQuery);

	List<Object> runOrder(SimpleQuery query, String string);

}
