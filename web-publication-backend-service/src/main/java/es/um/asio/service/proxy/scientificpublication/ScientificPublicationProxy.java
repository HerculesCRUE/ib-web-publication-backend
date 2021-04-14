package es.um.asio.service.proxy.scientificpublication;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.ScientificPublicationDto;
import es.um.asio.service.filter.scientificpublication.ScientificPublicationFilter;

public interface ScientificPublicationProxy {
	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<ScientificPublicationDto>
	 */
	Page<ScientificPublicationDto> findPaginated(ScientificPublicationFilter filter, Pageable pageable);
	
	ScientificPublicationDto find(String id);
}
