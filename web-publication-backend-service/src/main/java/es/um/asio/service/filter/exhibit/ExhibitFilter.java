package es.um.asio.service.filter.exhibit;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class ExpoFilter.
 */
@Getter
@Setter
@ToString(includeFieldNames = true)
public class ExhibitFilter extends Filter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6905629948517776190L;

	/**
	 * The abbreviation
	 */
	private String abbreviation;
	
	/**
	 * The contactInformation
	 */
	private String contactInformation;
	
	/**
	 * The date
	 */
	private String date;
	
	/**
	 * The description
	 */
	private String description;
	
	/**
	 * The id
	 */
	private String id;
	
	/**
	 * The locality
	 */
	private String locality;
	
	/**
	 * The locatedIn
	 */
	private String locatedIn;
	
	/**
	 * The title
	 */
	private String title;
	
	/**
	 * The participatedBy
	 */
	private String participatedBy;
}
