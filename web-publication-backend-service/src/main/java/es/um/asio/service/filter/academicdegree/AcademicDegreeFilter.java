package es.um.asio.service.filter.academicdegree;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class AcademicDegreeFilter extends Filter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4353035988212287661L;

	/**
	 * The id
	 */
	private String id;
	
	/**
	 * The title
	 */
	private String title;
	
	/**
	 * The abbreviation
	 */
	private String abbreviation;
}
