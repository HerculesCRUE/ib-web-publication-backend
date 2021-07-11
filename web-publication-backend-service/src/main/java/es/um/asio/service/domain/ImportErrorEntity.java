package es.um.asio.service.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import es.um.asio.service.domain.DomainDataBase.Columns;
import es.um.asio.service.util.JpaConstants;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="ImportError")
@Getter
@Setter
@ToString(includeFieldNames = true)
public class ImportErrorEntity {

	/**
	 * The id.
	 */
	@Id
	@GeneratedValue(generator = JpaConstants.HIBERNATE_UUID_GENERATOR_NAME)
	@GenericGenerator(name = JpaConstants.HIBERNATE_UUID_GENERATOR_NAME, strategy = JpaConstants.HIBERNATE_UUID_GENERATOR_STRATEGY)
	@Column(name = Columns.ENTITY_ID)
	@EqualsAndHashCode.Include
	private String entityId;
	
	/**
	 * The jobExecutionId
	 */
	private String jobExecutionId;
	
	/**
	 * The description
	 */
	private String description;
	
	/**
	 * Default constructor
	 */
	public ImportErrorEntity() {}
}
