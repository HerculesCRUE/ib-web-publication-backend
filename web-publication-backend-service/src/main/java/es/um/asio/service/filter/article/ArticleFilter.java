package es.um.asio.service.filter.article;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class ArticleFilter.
 */
@Getter
@Setter
@ToString(includeFieldNames = true)
public class ArticleFilter extends Filter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7608048444987210263L;
	
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
	 * The title
	 */
	private String title;
	
	/* Fields not included in model */
	
	/**
	 * The year from
	 */
	private String dateFrom;
	
	/**
	 * The year to
	 */
	private String dateTo;
}
