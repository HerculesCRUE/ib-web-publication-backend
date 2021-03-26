package es.um.asio.service.filter.researchgroup;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class ResearchGroupFilter.
 */
@Getter
@Setter
@ToString(includeFieldNames = true)
public class ResearchGroupFilter extends Filter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -701575400923362443L;
	
	/**
	 * The abbreviation
	 */
	private String abbreviation;
	
	/**
	 * The description
	 */
	private String description;
	
	/**
	 * The endDate
	 */
	private String endDate;
	
	/**
	 * The excellenceLabel
	 */
	private String excellenceLabel;
	
	/**
	 * The id
	 */
	private String id;
	
	/**
	 * The startDate
	 */
	private String startDate;
	
	/**
	 * The title
	 */
	private String title;
}
