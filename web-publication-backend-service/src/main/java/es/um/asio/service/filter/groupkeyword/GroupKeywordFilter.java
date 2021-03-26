package es.um.asio.service.filter.groupkeyword;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class GroupKeywordFilter.
 */
@Getter
@Setter
@ToString(includeFieldNames = true)
public class GroupKeywordFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8695320256272979724L;

	/**
	 * The id
	 */
	private String id;
	
	/**
	 * The keyword
	 */
	private String keyword;
}
