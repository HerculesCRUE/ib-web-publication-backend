package es.um.asio.service.proxy.phdsupervisingrelationship;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.PhdSupervisingRelationshipDto;
import es.um.asio.service.filter.phdsupervisingrelationship.PhdSupervisingRelationshipFilter;

public interface PhdSupervisingRelationshipProxy {
	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<PhdSupervisingRelationshipDto>
	 */
	Page<PhdSupervisingRelationshipDto> findPaginated(PhdSupervisingRelationshipFilter filter, Pageable pageable);
}
