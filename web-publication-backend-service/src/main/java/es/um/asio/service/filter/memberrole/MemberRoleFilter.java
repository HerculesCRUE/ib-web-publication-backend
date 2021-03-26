package es.um.asio.service.filter.memberrole;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class MemberRoleFilter.
 */
@Getter
@Setter
@ToString(includeFieldNames = true)
public class MemberRoleFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2132579210182390568L;

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
}
