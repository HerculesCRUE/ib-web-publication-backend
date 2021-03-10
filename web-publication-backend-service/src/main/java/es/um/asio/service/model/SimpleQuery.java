package es.um.asio.service.model;

import java.io.Serializable;

import org.springframework.data.domain.Pageable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class PageableQuery.
 */
@Getter
@Setter
@ToString(includeFieldNames = true)
public class SimpleQuery implements Serializable {

	private static final long serialVersionUID = -5600773325770247728L;
	
	/** The entity. */
	private Entity entity;
	
	/** The filter. */
	private String filters;
	
	
	public SimpleQuery(Entity entity, String filters) {
		super();
		this.entity = entity;
		this.filters = filters;
	}

}
