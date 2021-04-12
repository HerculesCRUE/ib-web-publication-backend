package es.um.asio.service.filter.phdsupervisingrelationship;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class PhdSupervisingRelationshipFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8594751674320579831L;

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
