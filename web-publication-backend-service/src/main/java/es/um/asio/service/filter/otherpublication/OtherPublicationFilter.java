package es.um.asio.service.filter.otherpublication;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class OtherPublicationFilter.
 */
@Getter
@Setter
@ToString(includeFieldNames = true)
public class OtherPublicationFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3219091447390167241L;

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
	 * The ocicnum
	 */
	private String ocicnum;
	
	/**
	 * The date
	 */
	private String title;
	
	/**
	 * The types
	 */
	private String types;
	
	/* Fields not included in model */
	
	/**
	 * The year from
	 */
	private String dateFrom;
	
	/**
	 * The year to
	 */
	private String dateTo;
	
	/**
	 * The author id.
	 */
	private String authorId;
}
