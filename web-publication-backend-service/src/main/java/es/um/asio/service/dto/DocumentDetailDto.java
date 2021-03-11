package es.um.asio.service.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class DocumentDto.
 */
@Getter
@Setter
public class DocumentDetailDto extends DocumentDto {
	
	/**
	 * The author
	 */
	private String correspondingAuthor;
	
	/**
	 * The keyword (Palabra clave)
	 */
	private String freetextKeyword;
	
	/**
	 * The corresponding organization. 
	 */
	private String correspondingOrganization;
	
	/** The Abstract. */
	private String _abstract;
	
	/** The List of authors. */
	private List<String> authorList;
}
