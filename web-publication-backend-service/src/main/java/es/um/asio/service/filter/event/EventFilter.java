package es.um.asio.service.filter.event;

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
public class EventFilter extends Filter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9066425157947222584L;
	
	/**
	 * The date
	 */
	private String id;
	
	/**
	 * The title
	 */
	private String title;
	
	/** The date. */
	private String date;
	
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
	


	/** The participant id. */
	private String participantId;
}
