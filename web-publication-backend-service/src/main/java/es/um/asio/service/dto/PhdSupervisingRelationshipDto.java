package es.um.asio.service.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * The class PhdSupervisingRelationshipDto
 */
@Getter
@Setter
public class PhdSupervisingRelationshipDto {

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
