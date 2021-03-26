package es.um.asio.service.filter.internship;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class InternshipFilter.
 */
@Getter
@Setter
@ToString(includeFieldNames = true)
public class InternshipFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7597246301153498453L;

	/**
	 * The id
	 */
	private String id;
	
	/**
	 * The title
	 */
	private String title;
	
	/**
	 * The contactInformation
	 */
	private String contactInformation;
	
	/**
	 * The description
	 */
	private String description;
	
	/**
	 * The endDate
	 */
	private String endDate;
	
	/**
	 * The locality
	 */
	private String locality;
	
	/**
	 * The locatedIn
	 */
	private String locatedIn;
	
	/**
	 * The participatedBy
	 */
	private String participatedBy;
	
	/**
	 * The startDate
	 */
	private String startDate;
}
