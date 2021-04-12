package es.um.asio.service.filter.masterthesis;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class MasterThesisFilter.
 */
@Getter
@Setter
@ToString(includeFieldNames = true)
public class MasterThesisFilter extends Filter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3901458210253686293L;
	
	/**
	 * The abbreviation
	 */
	private String abbreviation;
	
	/**
	 * The date
	 */
	private String date;
	
	/**
	 * The doi
	 */
	private String doi;
	
	/**
	 * The endPage
	 */
	private String endPage;
	
	/**
	 * The id
	 */
	private String id;
	
	/**
	 * The title
	 */
	private String title;
	
	/**
	 * The placeOfPublication
	 */
	private String placeOfPublication;
	
	/**
	 * The publishedIn
	 */
	private String publishedIn;
	
	/**
	 * The startPage
	 */
	private String startPage;
	
	/**
	 * The summary
	 */
	private String summary;
	
	/**
	 * The university
	 */
	private String university;
}
