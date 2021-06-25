package es.um.asio.service.service.dataimporter;

import java.util.List;

import es.um.asio.service.domain.importer.JobExecutionError;

public interface DataImporterService {
	
	/**
	 * Obtiene el listado de errores de una importación
	 * 
	 * @param id
	 * @param type
	 * @return DocumentDto
	 */
	List<JobExecutionError> findErrors(Long id);
}
