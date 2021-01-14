package es.um.asio.service.filter.book;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class BookFilter.
 */
@Getter
@Setter
@ToString(includeFieldNames = true)
public class BookFilter extends Filter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8003420601843949425L;

	/**
	 * The anyo
	 */
	private String anyo;
	
	/**
	 * The coautoria
	 */
	private String coautoria;
	
	/**
	 * The id
	 */
	private String id;
	
	/**
	 * The name
	 */
	private String name;
	
	/**
	 * The year from
	 */
	private String yearFrom;
	
	/**
	 * The year to
	 */
	private String yearTo;
}
