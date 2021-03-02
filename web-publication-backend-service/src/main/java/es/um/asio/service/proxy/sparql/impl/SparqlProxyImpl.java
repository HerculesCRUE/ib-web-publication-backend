/*
 * 
 */
package es.um.asio.service.proxy.sparql.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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


}
