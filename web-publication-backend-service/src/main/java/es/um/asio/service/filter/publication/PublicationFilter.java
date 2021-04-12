package es.um.asio.service.filter.publication;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class PublicationFilter extends Filter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5342704688912575940L;

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
