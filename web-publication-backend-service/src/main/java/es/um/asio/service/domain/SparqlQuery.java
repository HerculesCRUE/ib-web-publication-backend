package es.um.asio.service.domain;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(includeFieldNames = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SparqlQuery extends DomainDataBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8310471637062928081L;

	private String username;

	private String sparqlQuery;

	private String sparqlName;

}
