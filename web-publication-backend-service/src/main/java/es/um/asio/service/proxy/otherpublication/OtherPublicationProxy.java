package es.um.asio.service.proxy.otherpublication;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.OtherPublicationDto;
import es.um.asio.service.filter.otherpublication.OtherPublicationFilter;

public interface OtherPublicationProxy {

	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<OtherPublicationDto>
	 */
	Page<OtherPublicationDto> findPaginated(OtherPublicationFilter filter, Pageable pageable);
	
	/**
	 * Obtiene un OtherPublicationo
	 * 
	 * @param id
	 * @param type
	 * @return OtherPublicationDto
	 */
	OtherPublicationDto find(String id, String type);
}
