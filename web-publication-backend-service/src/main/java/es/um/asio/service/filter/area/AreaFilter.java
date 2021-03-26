package es.um.asio.service.filter.area;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class AreaFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3072316678862549072L;

	private Integer year;
}
