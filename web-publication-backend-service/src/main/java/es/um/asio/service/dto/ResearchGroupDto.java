package es.um.asio.service.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class ResearchGroupDto.
 */
@Getter
@Setter
public class ResearchGroupDto {
	
	/**
	 * The id
	 */
	private String id;
	
	/**
	 * The title
	 */
	private String title;

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
	 * The startDate
	 */
	private String startDate;
	
	/**
	 * The ScientificDomain (uppercase S)
	 */
	private String ScientificDomain;
}
