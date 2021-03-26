package es.um.asio.service.filter.projectcontract;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class ProjectContractFilter.
 */
@Getter
@Setter
@ToString(includeFieldNames = true)
public class ProjectContractFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7747707220775622261L;

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
