package es.um.asio.service.filter.gorup;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class GroupFilter.
 */
@Getter
@Setter
@ToString(includeFieldNames = true)
public class GroupFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -677588846676706521L;

	/**
	 * The id
	 */
	private String id;
	
	/**
	 * The date
	 */
	private String date;
	
	/**
	 * The dedication
	 */
	private String dedication;
	
	/**
	 * The dedicationPercentage
	 */
	private String dedicationPercentage;
	
	/**
	 * The description
	 */
	private String description;
	
	/**
	 * The homepage
	 */
	private String homepage;
}
