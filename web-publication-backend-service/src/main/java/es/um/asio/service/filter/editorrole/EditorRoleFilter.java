package es.um.asio.service.filter.editorrole;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class EditorRoleFilter.
 */
@Getter
@Setter
@ToString(includeFieldNames = true)
public class EditorRoleFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5510902355571434657L;

	/**
	 * The id
	 */
	private String id;
	
	/**
	 * The id
	 */
	private String date;
	
	/**
	 * The id
	 */
	private String dedication;
	
	/**
	 * The id
	 */
	private String dedicationPercentage;
	
	/**
	 * The id
	 */
	private String description;
	
	/**
	 * The hasKnowledgeArea
	 */
	private String hasKnowledgeArea;
}
