package es.um.asio.service.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class ProjectDto.
 */
@Getter
@Setter
public class ProjectDetailDto {
	
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
	private String dateEnd;
	
	/**
	 * The foreseenJustificationDate
	 */
	private String foreseenJustificationDate;
	
	/**
	 * The keyword
	 */
	private String keyword;

	/**
	 * The modality
	 */
	private String modality;
	
	/**
	 * The needsEthicalValidation
	 */
	private String needsEthicalValidation;
	
	/**
	 * The projectClassification
	 */
	private String projectClassification;
	
	/** 
	 * The startDate 
	 */
	private String dateStart;
	
	/** 
	 * The status 
	 */
	private String status;
}
