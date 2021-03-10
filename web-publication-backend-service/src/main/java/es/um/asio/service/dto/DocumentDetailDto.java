package es.um.asio.service.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class DocumentDto.
 */
@Getter
@Setter
public class DocumentDetailDto extends DocumentDto{
	
	/**
	 * The author
	 */
	private String author;
	
	/**
	 * The keyword (Palabra clave)
	 */
	private String keyword;
	
	/**
	 * The corresponding organization. 
	 */
	private String correspondingOrganization;
	
	/** The Abstract. */
	private String Abstract;
	
	/** The List of authors. */
	private List<String> ListOfAuthors;
}
