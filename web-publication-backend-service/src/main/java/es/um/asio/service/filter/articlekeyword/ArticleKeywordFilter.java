package es.um.asio.service.filter.articlekeyword;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;

/**
 * The class ArticleKeywordFilter
 */
@Getter
@Setter
public class ArticleKeywordFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6082086857768781495L;
	
	/**
	 * The keyword
	 */
	private String keyword;
}
