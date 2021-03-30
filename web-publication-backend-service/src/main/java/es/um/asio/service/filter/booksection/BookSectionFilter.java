package es.um.asio.service.filter.booksection;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class BookSectionFilter.
 */
@Getter
@Setter
@ToString(includeFieldNames = true)
public class BookSectionFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4909499663065049225L;

	/**
	 * The date
	 */
	private String date;
	
	/**
	 * The doi
	 */
	private String doi;
	
	/**
	 * The edition
	 */
	private String edition;
	
	/**
	 * The endPage
	 */
	private String endPage;
	
	/**
	 * The id
	 */
	private String id;
	
	/**
	 * The placeOfPublication
	 */
	private String placeOfPublication;
	
	/**
	 * The startPage
	 */
	private String startPage;
	
	/**
	 * The title
	 */
	private String title;
	
	/**
	 * The bookId
	 */
	private String bookId;
}
