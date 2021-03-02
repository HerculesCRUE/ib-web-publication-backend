package es.um.asio.service.proxy.sparql;

import org.springframework.http.ResponseEntity;

public interface SparqlProxy {

	ResponseEntity<Object> run(String query, Boolean isFederated);

}
