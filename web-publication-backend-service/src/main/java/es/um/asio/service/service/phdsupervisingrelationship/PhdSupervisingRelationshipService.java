package es.um.asio.service.service.phdsupervisingrelationship;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.filter.phdsupervisingrelationship.PhdSupervisingRelationshipFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;

public interface PhdSupervisingRelationshipService {
	
	/**
	 * Find paginated.
	 *
	 * @param filter   the filter
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<FusekiResponse> findPaginated(PhdSupervisingRelationshipFilter filter, Pageable pageable);

	/**
	 * Retrieve entity.
	 *
	 * @return the entity
	 */
	Entity retrieveEntity();

	String filtersChunk(PhdSupervisingRelationshipFilter filter);
}
