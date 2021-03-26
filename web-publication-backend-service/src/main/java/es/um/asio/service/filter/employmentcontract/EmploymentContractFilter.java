package es.um.asio.service.filter.employmentcontract;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class EmploymentContractFilter.
 */
@Getter
@Setter
@ToString(includeFieldNames = true)
public class EmploymentContractFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6946010865439721109L;

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
