package es.um.asio.service.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class Entity.
 */
@Getter
@Setter
@ToString(includeFieldNames = true)
@EqualsAndHashCode
public class Subentity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -969930909950320919L;
	
	/** The filters. */
	private Map<String, String> filters;
	
	
	/** The field name. */
	//Name of the atribute equivalent to Object
	private String fieldName;
	
	/** The fields. */
	//Name of atributes we want to show in query
	private List<String> fields;
	
	
}
