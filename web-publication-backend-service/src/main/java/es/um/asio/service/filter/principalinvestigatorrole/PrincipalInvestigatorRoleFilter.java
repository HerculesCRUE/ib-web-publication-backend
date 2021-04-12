package es.um.asio.service.filter.principalinvestigatorrole;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class PrincipalInvestigatorRoleFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6701702161894850751L;

	/**
	 * The id
	 */
	private String id;

	/**
	 * The dedication
	 */
	private String dedication;

	/**
	 * The description
	 */
	private String description;

	/**
	 * The keyword
	 */
	private String keyword;

	/**
	 * The ordinal
	 */
	private String ordinal;
}
