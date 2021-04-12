package es.um.asio.service.filter.curriculumvitae;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class CurriculumVitaeFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1931439141595172424L;

	/**
	 * The id
	 */
	private String id;
	
	/**
	 * The correspondingAuthor
	 */
	private String correspondingAuthor;
}
