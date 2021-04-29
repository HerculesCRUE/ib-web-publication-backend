package es.um.asio.service.proxy.sparql;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import es.um.asio.service.dto.SparqlQueryDto;
import es.um.asio.service.filter.sparql.SparqlQueryFilter;

public interface SparqlProxy {

	ResponseEntity<Object> run(String query, Boolean isFederated);

	Page<SparqlQueryDto> findPaginated(SparqlQueryFilter filter, Pageable pageable);

	SparqlQueryDto save(SparqlQueryDto sparqlQueryDto);

	SparqlQueryDto update(SparqlQueryDto sparqlQueryDto);

	void delete(String id);

}
