package es.um.asio.service.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class EmploymentContractDto.
 */
@Getter
@Setter
public class EmploymentContractDto {
	
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
}
