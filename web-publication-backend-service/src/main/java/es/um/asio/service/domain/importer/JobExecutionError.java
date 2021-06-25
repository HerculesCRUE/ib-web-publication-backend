package es.um.asio.service.domain.importer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ImportError")
@Getter
@Setter
@NoArgsConstructor
public class JobExecutionError {

	/**
	 * The id.
	 */
	@Id
	@Column(name = "ENTITY_ID")
	@EqualsAndHashCode.Include
	private String entityId;
	
	private Long jobExecutionId;
	
	private String description;
	
}
