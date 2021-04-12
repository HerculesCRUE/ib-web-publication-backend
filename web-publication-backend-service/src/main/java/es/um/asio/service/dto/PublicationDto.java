package es.um.asio.service.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * The class PublicationDto
 */
@Getter
@Setter
public class PublicationDto {
	
	/**
	 * The id
	 */
	private String id;

	/**
	 * The title
	 */
	private String title;
	
	/**
	 * The date
	 */
	private String date;

	
	/**
	 * The doi
	 */
	private String doi;
	
	/**
	 * The keyword
	 */
	private String keyword;
	
	/**
	 * The summary
	 */
	private String summary;
	
	/**
	 * The pageEnd (starts with P)
	 */
	private String pageEnd;
	
	/**
	 * The pageStart (starts with P)
	 */
	private String pageStart;

}
