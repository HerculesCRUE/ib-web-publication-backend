package es.um.asio.service.filter.knowledgearea;

import es.um.asio.service.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class KnowledgeAreaFilter extends Filter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4805994023297912727L;
	
	/**
	 * The title
	 */
	private String title;
}
