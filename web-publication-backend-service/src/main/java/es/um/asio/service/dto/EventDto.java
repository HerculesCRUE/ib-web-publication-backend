package es.um.asio.service.dto;

import lombok.Getter;
import lombok.Setter;

// TODO update with future changes from ETL
/**
 * The Class EventDetailDto.
 */


@Getter
@Setter
public class EventDto {
	
	/** The id. */
	private String id;
	
	/** The title. */
	private String title;
	
	/** The title. */
	private String type;
	
	/**  The date. */
	private String date;
	
	/** The location. */
	private String locality;

}
