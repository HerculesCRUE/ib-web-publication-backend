package es.um.asio.service.service.person;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.filter.person.PersonFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;

public interface PersonService {

	/**
	 * Find paginated.
	 *
	 * @param filter   the filter
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<FusekiResponse> findPaginated(PersonFilter filter, Pageable pageable);
	

	/**
	 * Find paginated by project.
	 *
	 * @param filter   the filter
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<FusekiResponse> findPaginatedByProject(PersonFilter filter, Pageable pageable);

	/**
	 * Find.
	 *
	 * @param id the id
	 * @return the fuseki response
	 */
	List<Object> find(String id);

	
	/**
	 * Retrieve entity filtered by project.
	 *
	 * @return the entity
	 */
	public Entity retrieveEntityByProject(PersonFilter filter);
	
	/**
	 * Retrieve the detail of an entity.
	 *
	 * @return the entity
	 */
	public Entity retrieveDetailEntity();

	String filtersChunk(String id);

	String filtersChunk(PersonFilter filter);

	List<Object> getArea();
}
