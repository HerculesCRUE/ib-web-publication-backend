package es.um.asio.service.filter.researcherrole;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class ResearcherRoleFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3943159395465597308L;

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
