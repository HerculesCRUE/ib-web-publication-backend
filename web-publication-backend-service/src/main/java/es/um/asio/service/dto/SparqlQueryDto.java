package es.um.asio.service.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SparqlQueryDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7077051156392296792L;

	private String entityId;

	private String username;

	private String sparqlQuery;

	private String sparqlName;
}
