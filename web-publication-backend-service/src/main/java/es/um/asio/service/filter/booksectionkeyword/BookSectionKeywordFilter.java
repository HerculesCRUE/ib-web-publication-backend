package es.um.asio.service.filter.booksectionkeyword;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class BookSectionKeywordFilter.
 */
@Getter
@Setter
@ToString(includeFieldNames = true)
public class BookSectionKeywordFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 699144720431269731L;
	
	/**
	 * The keyword
	 */
	private String keyword;
}
