package es.um.asio.service.filter.fundingprogram;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class FundingProgramFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1088536335384621128L;

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
	 * The description
	 */
	private String description;
}
