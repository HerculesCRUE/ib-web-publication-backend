package es.um.asio.service.filter.document;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class DocumentFilter.
 */
@Getter
@Setter
@ToString(includeFieldNames = true)
public class DocumentFilter extends Filter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9066425157947222584L;
	
	/**
	 * The date
	 */
	private String id;

	/**
	 * The date
	 */
	private String date;
	
	/**
	 * The title
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
	 * The year to
	 */
	private String authorId;
	
	/** 
	 * The author id. 
	 * */
	private String organizationId;
}
