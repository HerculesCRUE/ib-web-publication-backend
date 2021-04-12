package es.um.asio.service.filter.awardeddegree;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class AwardedDegreeFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 459175427917254773L;

	/**
	 * The id
	 */
	private String id;
	
	/**
	 * The dateTimeValue
	 */
	private String dateTimeValue;
}
