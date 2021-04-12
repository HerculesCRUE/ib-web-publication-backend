package es.um.asio.service.filter.researcherposition;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class ResearcherPositionFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4752522015737894861L;

	/**
	 * The id
	 */
	private String id;
	
	/**
	 * The ordinal
	 */
	private String ordinal;
}
