package es.um.asio.service.service.patent;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.filter.patent.PatentFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;

/**
 * Servicio para la entidad Patente
 *
 */
public interface PatentService {
	/**
	 * Find paginated.
	 *
	 * @param filter   the filter
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<FusekiResponse> findPaginated(PatentFilter filter, Pageable pageable);

	/**
	 * Retrieve entity.
	 *
	 * @return the entity
	 */
	Entity retrieveEntity();

	String filtersChunk(PatentFilter filter);

	String getArea();
}
