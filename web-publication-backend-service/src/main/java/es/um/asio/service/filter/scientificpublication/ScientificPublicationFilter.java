package es.um.asio.service.filter.scientificpublication;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class ScientificPublicationFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7066166967397756389L;

	/**
	 * The id
	 */
	private String id;
	
	/**
	 * The title
	 */
	private String title;
	
	/**
	 * The abbreviation
	 */
	private String abbreviation;
	
	/**
	 * The date
	 */
	private String date;
	
	/**
	 * The description
	 */
	private String description;
	
	/**
	 * The homepage
	 */
	private String homepage;
	
	/**
	 * The isStartup
	 */
	private String isStartup;
	
	/**
	 * The keyword
	 */
	private String keyword;
	
	/**
	 * The publicCompany
	 */
	private String publicCompany;
}
