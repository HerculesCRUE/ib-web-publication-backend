package es.um.asio.service.filter.bookkeyword;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;

/**
 * The class BookKeywordFilter
 */
@Getter
@Setter
public class BookKeywordFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5113863767101626437L;
	
	/**
	 * The keyword
	 */
	private String keyword;
}
