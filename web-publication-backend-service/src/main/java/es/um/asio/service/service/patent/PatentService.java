package es.um.asio.service.service.patent;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.filter.patent.PatentFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;

/**
 * Servicio para la entidad Patente.
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
	 * Find.
	 *
	 * @param id the id
	 * @return the list
	 */
	List<Object> find(String id);

	/**
	 * Retrieve entity.
	 *
	 * @return the entity
	 */
	Entity retrieveEntity(PatentFilter filter);

	/**
	 * Retrieve detail entity.
	 *
	 * @return the entity
	 */
	Entity retrieveDetailEntity();

	/**
	 * Filters chunk.
	 *
	 * @param filter the filter
	 * @return the string
	 */
	String filtersChunk(PatentFilter filter);

	/**
	 * Filters chunk.
	 *
	 * @param id the id
	 * @return the string
	 */
	String filtersChunk(String id);

	/**
	 * Gets the area.
	 *
	 * @return the area
	 */
	List<Object> getbyOrganization();
}
