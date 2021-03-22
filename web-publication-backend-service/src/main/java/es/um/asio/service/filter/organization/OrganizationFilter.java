package es.um.asio.service.filter.organization;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class OrganizationFilter.
 */
@Getter
@Setter
@ToString(includeFieldNames = true)
public class OrganizationFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 148224669644952663L;

	/**
	 * The id
	 */
	private String id;

	/**
	 * The title
	 */
	private String title;

	/**
	 * The types
	 */
	private String types;

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
	 * The homepage
	 */
	private String homepage;

	/**
	 * The isStartup
	 */
	private String isStartup;

	/**
	 * The publicCompany
	 */
	private String publicCompany;

	/**
	 * The startDate
	 */
	private String startDate;
}
