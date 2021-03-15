package es.um.asio.service.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class DocumentDto.
 */
@Getter
@Setter
public class DocumentDetailDto extends DocumentDto {
	
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
	 * The edition
	 */
	private String edition;
	
	/**
	 * The endPage
	 */
	private String endPage;
	
	/**
	 * The iccn
	 */
	private String iccn;
	
	/**
	 * The placeOfPublication
	 */
	private String placeOfPublication;
	
	/**
	 * The publishedIn
	 */
	private String publishedIn;
	
	/**
	 * The startPage
	 */
	private String startPage;
	
	/**
	 * The type
	 */
	private String type;
	
	/**
	 * The summary
	 */
	private String summary;
}
