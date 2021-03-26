package es.um.asio.service.filter.patent;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class PatentFilter.
 */
@Getter
@Setter
@ToString(includeFieldNames = true)
public class PatentFilter extends Filter {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8008993664632532940L;

	/** 
	 * The dateIssued. 
	 * */
	private String dateIssued;
	
	/** 
	 * The doi. 
	 * */
	private String doi;
	
	/** 
	 * The endDate. 
	 * */
	private String endDate;
	
	/** 
	 * The endPage. 
	 * */
	private String endPage;
	
	/** 
	 * The id. 
	 * */
	private String id;
	
	/** 
	 * The keyword. 
	 * */
	private String keyword;
	
	/** 
	 * The mode. 
	 * */
	private String mode;
	
	/**
	 * The pageEnd
	 */
	private String pageEnd;
	
	/**
	 * The pageStart
	 */
	private String pageStart;
	
	/** 
	 * The startDate. 
	 * */
	private String startDate;
	
	/** 
	 * The startPage. 
	 * */
	private String startPage;
	
	/** 
	 * The title. 
	 * */
	private String title;
	
	/** 
	 * The summary. 
	 * */
	private String summary;
	
	
	/** 
	 * The author id. 
	 * */
	private String authorId;
}
