/*
 * 
 */
package es.um.asio.back.controller.sparql;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.proxy.sparql.SparqlProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


/**
 * The Class SparqlController.
 */
@RestController
@RequestMapping(SparqlController.Mappings.BASE)
public class SparqlController {

	private static final String QUERY = "query";

	@Autowired
	private SparqlProxy sparqlProxy; 
	
	private final Logger logger = LoggerFactory.getLogger(SparqlController.class);
	
		
	/**
	 * Search proyects.
	 *
	 * @param body the body
	 * @return the response entity
	 */
	@RequestMapping(value = SparqlController.Mappings.SPARQL, method = RequestMethod.POST,
	        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
	        produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> searchSPARQL(@RequestParam Map<String, String> body) {
		logger.info("Searching sparql query");
		
		ResponseEntity<Object> result = null;
		
		if(body != null && StringUtils.isNotBlank(body.get(SparqlController.QUERY))) {
			result = sparqlProxy.run(body.get(SparqlController.QUERY), false);
		} else {
			logger.error("Empty sparql query");
		}
		
		return result;
	}
	
	/**
	 * Search proyects.
	 *
	 * @param body the body
	 * @return the response entity
	 */
	@RequestMapping(value = SparqlController.Mappings.SPARQL_FEDER, method = RequestMethod.POST,
	        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
	        produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> searchSPARQLFeder(@RequestParam Map<String, String> body) {
		logger.info("Searching sparql query");
		
		ResponseEntity<Object> result = null;
		
		if(body != null && StringUtils.isNotBlank(body.get(SparqlController.QUERY))) {
			result = sparqlProxy.run(body.get(SparqlController.QUERY), true);
		} else {
			logger.error("Empty sparql query");
		}
		
		return result;
	}
	
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/trellis";
		
		
		/** The Constant SPARQL. */
		protected static final String SPARQL = "/sparql";
		
		/** The Constant SPARQL. */
		protected static final String SPARQL_FEDER = "/sparqlfeder";
	}
}
