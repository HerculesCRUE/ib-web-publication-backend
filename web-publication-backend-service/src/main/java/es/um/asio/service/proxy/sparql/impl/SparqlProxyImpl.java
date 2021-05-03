/*
 * 
 */
package es.um.asio.service.proxy.sparql.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.SparqlQueryDto;
import es.um.asio.service.filter.sparql.SparqlQueryFilter;
import es.um.asio.service.mapper.SparqlQueryMapper;
import es.um.asio.service.proxy.sparql.SparqlProxy;
import es.um.asio.service.service.sparql.SparqlExecQuery;

/**
 * The Class SparqlProxyImpl.
 */
@Service
public class SparqlProxyImpl implements SparqlProxy {

	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(SparqlProxyImpl.class);

	/** The sparql exec query. */
	@Autowired
	private SparqlExecQuery sparqlExecQuery;

	@Autowired
	SparqlQueryMapper mapper;

	/**
	 * Run.
	 *
	 * @param query the query
	 * @return the response entity
	 */
	@Override
	public ResponseEntity<Object> run(String query, Boolean isFederated) {
		logger.info("Running sparql {}", query);
		return sparqlExecQuery.callFusekiTrellis(query, isFederated);
	}

	@Override
	public Page<SparqlQueryDto> findPaginated(SparqlQueryFilter filter, Pageable pageable) {
		return this.mapper.convertSparqlQueryPageToDto(this.sparqlExecQuery.findPaginated(filter, pageable));
	}

	@Override
	public SparqlQueryDto save(SparqlQueryDto sparqlQueryDto) {
		return this.mapper.convertSparqlQueryToDto(
				this.sparqlExecQuery.save(this.mapper.convertSparqlQueryFromDto(sparqlQueryDto)));
	}

	@Override
	public SparqlQueryDto update(SparqlQueryDto sparqlQueryDto) {
		return this.mapper.convertSparqlQueryToDto(
				this.sparqlExecQuery.update(this.mapper.convertSparqlQueryFromDto(sparqlQueryDto)));
	}

	@Override
	public void delete(String id) {
		this.sparqlExecQuery.delete(id);

	}

}
