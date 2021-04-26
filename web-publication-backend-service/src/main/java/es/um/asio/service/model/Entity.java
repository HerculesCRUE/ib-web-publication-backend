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
public class Entity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -969930909950320919L;
	
	/** The fields. */
	private List<String> fields;
	
	/** The optionalFields. */
	private List<String> optionalFields;
	
	/**  The types. */
	private List<String> types;
	
	/**  The group. */
	private List<String> group;
	
	/**  The join map. */
	private Map<String, Map<String, String>> join;
	

	/** The subentities. */
	private List<Subentity> subentities;
	
	/** The entity. */
	private String entity;
	
	/**
	 * Instantiates a new entity.
	 *
	 * @param entity the entity
	 * @param fields the fields
	 */
	public Entity(String entity, String... fields) {
		this.entity = entity;
		this.fields = Arrays.asList(fields);  
	}
	
	/**
	 * Instantiates a new entity.
	 *
	 * @param entity the entity
	 * @param types the types
	 * @param fields the fields
	 */
	public Entity(String entity, List<String> types, String... fields) {
		this.entity = entity;
		this.types = types;
		this.fields = Arrays.asList(fields);  
	}
	
	/**
	 * Instantiates a new entity.
	 *
	 * @param entity the entity
	 * @param types the types
	 * @param group the groups
	 * @param fields the fields
	 */
	public Entity(String entity, List<String> types, List<String> group, String... fields) {
		this.entity = entity;
		this.types = types;
		this.group = group;
		this.fields = Arrays.asList(fields);  
	}
}
