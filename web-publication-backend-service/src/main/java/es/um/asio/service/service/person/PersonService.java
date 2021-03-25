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
	 * Find.
	 *
	 * @param id the id
	 * @return the fuseki response
	 */
	List<Object> find(String id);

	/**
	 * Retrieve entity.
	 *
	 * @return the entity
	 */
	Entity retrieveEntity();

	String filtersChunk(String id);

	String filtersChunk(PersonFilter filter);

	String getArea();
}
