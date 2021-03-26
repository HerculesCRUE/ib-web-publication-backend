package es.um.asio.service.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * The class ResearchAccreditationDto
 */
@Getter
@Setter
public class ResearchAccreditationDto {
	
	/**
	 * The id
	 */
	private String id;
	
	/**
	 * The title
	 */
	private String title;

	/**
	 * The dateIssued
	 */
	private String dateIssued;
	
	/**
	 * The description
	 */
	private String description;
	
	/**
	 * The governingAuthority
	 */
	private String governingAuthority;
	
	/**
	 * The hasSubjectArea
	 */
	private String hasSubjectArea;
}
