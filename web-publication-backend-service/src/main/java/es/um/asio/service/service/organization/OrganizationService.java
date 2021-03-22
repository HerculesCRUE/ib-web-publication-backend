package es.um.asio.service.service.organization;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.filter.organization.OrganizationFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;

public interface OrganizationService {

	/**
	 * Find paginated.
	 *
	 * @param filter   the filter
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<FusekiResponse> findPaginated(OrganizationFilter filter, Pageable pageable);

	/**
	 * Retrieve entity.
	 *
	 * @return the entity
	 */
	Entity retrieveEntity();

	String filtersChunk(OrganizationFilter filter);

	String filtersChunk(String id);

	List<Object> find(String id, String type);

	Entity retrieveEntity(String type);

	Entity retrieveEntity(OrganizationFilter filter);
}
