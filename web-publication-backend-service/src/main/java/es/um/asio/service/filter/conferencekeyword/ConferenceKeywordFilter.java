package es.um.asio.service.filter.conferencekeyword;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class ConferenceKeywordFilter.
 */
@Getter
@Setter
@ToString(includeFieldNames = true)
public class ConferenceKeywordFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6062027394107619685L;
	
	/**
	 * The keyword
	 */
	private String keyword;
}
