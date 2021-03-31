package es.um.asio.service.filter.project;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class ProjectFilter.
 */
@Getter
@Setter
@ToString(includeFieldNames = true)
public class ProjectFilter extends Filter {

	private static final long serialVersionUID = -1586947094505235656L;

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
	private String endDate;
	
	/**
	 * The foreseenJustificationDate
	 */
	private String foreseenJustificationDate;
	
	/** 
	 * The id
	 */
	private String id;
	
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
	private String startDate;
	
	/** 
	 * The status 
	 */
	private String status;
	
	/** 
	 * The title 
	 */
	private String title;
	
	/** The author id. */
	private String authorId;
}
