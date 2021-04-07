package es.um.asio.service.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class OrganizationDto.
 */

@Getter
@Setter
public class OrganizationDetailDto {

	/** The id. */
	private String id;

	/** The title. */
	private String title;

	/** The abbreviation. */
	private String abbreviation;

	/** The description. */
	private String description;

	/** The endDate. */
	private String dateEnd;

	/** The homepage. */
	private String homepage;

	/** The isStartup. */
	private String isStartup;

	/** The publicCompany. */
	private String publicCompany;

	/** The startDate. */
	private String dateStart;

	/** The has knowledge area. */
	private String hasKnowledgeArea;

	/** The has contact info. */
	private String hasContactInfo;

	/** The provides. */
	private String provides;

	/** The located in. */
	private String locatedIn;

	/** The has acreditation. */
	private String hasAcreditation;

	/** The has predecessor organization. */
	private String hasPredecessorOrganization;

	/** The contains. */
	// http://purl.org/roh/mirror/obo/ro#BFO_0000051
	private String contains;

	/** The date time interval. */
//	private String dateTimeInterval;

	private String participates;

	/** The has successor organization. */
	private String hasSuccessorOrganization;

	/** The has reservable. */
	private String hasReservable;

	/** The freetext keyword. */
	private String freetextKeyword;

	/** The related by. */
	private String relatedBy;

	/**
	 * The type
	 */
	private String type;
}
