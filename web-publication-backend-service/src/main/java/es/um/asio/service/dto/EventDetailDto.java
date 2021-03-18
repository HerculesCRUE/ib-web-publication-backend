package es.um.asio.service.dto;

import lombok.Getter;
import lombok.Setter;

// TODO update with future changes from ETL
/**
 * The Class EventDetailDto.
 */


@Getter
@Setter
public class EventDetailDto {
	
	/** The id. */
	private String id;
	
	/** The title. */
	private String title;
	
	/** The abbreviation. */
	private String abbreviation;
	
	/** The contact information. */
	private String contactInformation;
	
	/**  The date. */
	private String date;
	
	/** The description. */
	private String description;
	
	/** The location. */
	private String locality;
	
	/** The located in. */
	private String locatedIn;
	
	/** The presents. */
	private String presents;
	
//	/** The keyword. */
//	private String freetextKeyword;
//	
//	/** The has subject area. */
//	private String hasSubjectArea;
}
