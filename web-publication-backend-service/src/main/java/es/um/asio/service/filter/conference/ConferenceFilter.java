package es.um.asio.service.filter.conference;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// TODO: Auto-generated Javadoc
/**
 * The Class CongressFilter.
 */

/**
 * Gets the participan id.
 *
 * @return the participan id
 */

/**
 * Gets the participan id.
 *
 * @return the participan id
 */
@Getter

/**
 * Sets the participan id.
 *
 * @param participanId the new participan id
 */

/**
 * Sets the participan id.
 *
 * @param participanId the new participan id
 */
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(includeFieldNames = true)
public class ConferenceFilter extends Filter {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8763345133106144921L;
	
	/** The abbreviation. */
	private String abbreviation;
	
	/** The contactInformation. */
	private String contactInformation;
	
	/** The date. */
	private String date;
	
	/** The description. */
	private String description;
	
	/** The id. */
	private String id;
	
	/** The locality. */
	private String locality;
	
	/** The locatedIn. */
	private String locatedIn;
	
	/** The presents. */
	private String presents;
	
	/** The title. */
	private String title;

	/** The participant id. */
	private String participantId;
}
