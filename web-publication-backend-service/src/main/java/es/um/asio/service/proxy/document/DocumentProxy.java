package es.um.asio.service.proxy.document;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.DocumentDto;
import es.um.asio.service.filter.document.DocumentFilter;

public interface DocumentProxy {

	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<DocumentDto>
	 */
	Page<DocumentDto> findPaginated(DocumentFilter filter, Pageable pageable);
	
	/**
	 * Obtiene un documento
	 * 
	 * @param id
	 * @param type
	 * @return DocumentDto
	 */
	DocumentDto find(String id, String type);
}
