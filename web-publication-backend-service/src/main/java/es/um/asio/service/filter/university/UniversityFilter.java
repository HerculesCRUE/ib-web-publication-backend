package es.um.asio.service.filter.university;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class UniversityFilter.
 */
@Getter
@Setter
@ToString(includeFieldNames = true)
public class UniversityFilter extends Filter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7179546021182770177L;
	
	/**
	 * The id
	 */
	private String id;
	
	/**
	 * The title
	 */
	private String title;
	
	/**
	 * The university
	 */
	private String university;
}
