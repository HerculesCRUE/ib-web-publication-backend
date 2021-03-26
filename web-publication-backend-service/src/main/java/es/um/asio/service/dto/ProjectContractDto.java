package es.um.asio.service.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class ProjectContractDto.
 */
@Getter
@Setter
public class ProjectContractDto {

	/** 
	 * The id
	 */
	private String id;
	
	/** 
	 * The attachment
	 */
	private String attachment;
	
	/** 
	 * The endDate
	 */
	private String endDate;
	
	/** 
	 * The startDate
	 */
	private String startDate;
	
	/** 
	 * The summary
	 */
	private String summary;
	
	/**
	 * The Publication (uppercase P)
	 */
	private String publication;
	
	/**
	 * The SubjectArea (uppercase S)
	 */
	private String subjectArea;
}
