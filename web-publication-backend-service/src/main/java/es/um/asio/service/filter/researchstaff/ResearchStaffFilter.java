package es.um.asio.service.filter.researchstaff;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class ResearchStaffFilter.
 */
@Getter
@Setter
@ToString(includeFieldNames = true)
public class ResearchStaffFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4045870502145048454L;

	/**
	 * The name
	 */
	private String name;
	
	/**
	 * The title
	 */
	private String title;
	
	private String knowledgeArea;
}
