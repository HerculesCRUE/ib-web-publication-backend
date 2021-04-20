package es.um.asio.service.filter.person;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class PersonFilter.
 */
@Getter
@Setter
@ToString(includeFieldNames = true)
public class PersonFilter extends Filter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5227214432697152423L;
	
	/**
	 * The birthDate
	 */
	private String birthDate;
	
	/**
	 * The description
	 */
	private String description;
	
	/**
	 * The firstName
	 */
	private String firstName;
	
	/**
	 * The gender
	 */
	private String gender;
	
	/**
	 * The hasContactInfo
	 */
	private String hasContactInfo;
	
	/**
	 * The homepage
	 */
	private String homepage;
	
	/**
	 * The id
	 */
	private String id;
	
	/**
	 * The image
	 */
	private String image;
	
	/**
	 * The name
	 */
	private String name;
	
	/**
	 * The nickname
	 */
	private String nickname;
	
	/**
	 * The personalMailBox
	 */
	private String personalMaibox;
	
	/**
	 * The researchLine
	 */
	private String researchLine;
	
	/**
	 * The subjectArea
	 */
	private String subjectArea;
	
	/**
	 * The surname
	 */
	private String surname;
	
	/**
	 * The taxId
	 */
	private String taxId;
	
	/**
	 * The title
	 */
	private String title;
	
	/**
	 * The project id
	 */
	private String projectId;
}
