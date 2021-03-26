package es.um.asio.service.filter.supervisorrole;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class SupervisorRoleFilter.
 */
@Getter
@Setter
@ToString(includeFieldNames = true)
public class SupervisorRoleFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5040950251860020114L;

	/**
	 * The id
	 */
	private String id;
	
	/**
	 * The date
	 */
	private String date;
	
	/**
	 * The dedication
	 */
	private String dedication;
	
	/**
	 * The dedicationPercentage
	 */
	private String dedicationPercentage;
	
	/**
	 * The description
	 */
	private String description;
	
	/**
	 * The hasKnowledgeArea
	 */
	private String hasKnowledgeArea;
	
	/**
	 * The inheresIn
	 */
	private String inheresIn;
}
