package es.um.asio.service.filter.academicpublication;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class AcademicPublicationFilter.
 */
@Getter
@Setter
@ToString(includeFieldNames = true)
public class AcademicPublicationFilter extends Filter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1338237707383498312L;
	
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
	
	/** The directed by. */
	private String directedBy;
}
