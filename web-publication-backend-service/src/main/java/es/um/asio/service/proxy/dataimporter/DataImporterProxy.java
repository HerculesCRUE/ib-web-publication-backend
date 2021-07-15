package es.um.asio.service.proxy.dataimporter;

import java.util.List;

public interface DataImporterProxy {

	/**
	 * Obtiene el listado de errores de una importación
	 * 
	 * @param id
	 * @param type
	 * @return DataImporterErrorDto
	 */
	List<String> findErrors(String id);
}
