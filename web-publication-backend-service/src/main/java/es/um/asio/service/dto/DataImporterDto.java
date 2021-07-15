package es.um.asio.service.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class DataImporterDto.
 */
@Getter
@Setter
public class DataImporterDto {
	
	/**
	 * The id
	 */
	private String id;
	
	/**
	 * The date
	 */
	private Date date;
	
	/**
	 * The user
	 */
	private String user;
	
	/**
	 * The type
	 */
	private String type;
	
	/**
	 * The cron
	 */
	private String cron;
	
	/**
	 * The params
	 */
	private String params;
	
	/**
	 * The importer data error object
	 */
	private DataImporterErrorDto importerErrors;

}
