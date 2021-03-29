package es.um.asio.service.service.project;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.filter.project.ProjectFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;

public interface ProjectService {

	/**
	 * Find paginated.
	 *
	 * @param filter   the filter
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<FusekiResponse> findPaginated(ProjectFilter filter, Pageable pageable);
	
	
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
	Entity retrieveEntity();
	
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
	String filtersChunk(ProjectFilter filter);
	
	/**
	 * Filters chunk.
	 *
	 * @param id the id
	 * @return the string
	 */
	String filtersChunk(String id);

	String getbyInvestigation();
}
