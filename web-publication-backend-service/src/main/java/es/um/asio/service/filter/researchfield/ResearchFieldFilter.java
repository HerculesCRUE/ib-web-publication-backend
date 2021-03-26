package es.um.asio.service.filter.researchfield;

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
public class ResearchFieldFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2904160776813186884L;

	/**
	 * The id
	 */
	private String id;
	
	/**
	 * The title
	 */
	private String title;
	
	/**
	 * The type
	 */
	private String type;
}
