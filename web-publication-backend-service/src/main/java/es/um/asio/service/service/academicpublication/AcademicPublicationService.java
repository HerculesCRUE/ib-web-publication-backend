package es.um.asio.service.service.academicpublication;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.filter.academicpublication.AcademicPublicationFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;

public interface AcademicPublicationService {

	/**
	 * Find paginated.
	 *
	 * @param filter   the filter
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<FusekiResponse> findPaginated(AcademicPublicationFilter filter, Pageable pageable);
	
	
	/**
	 * Find.
	 *
	 * @param id the id
	 * @param type the type
	 * @return the fuseki response
	 */
	List<Object> find(String id, String type);

	/**
	 * Retrieve entity.
	 *
	 * @param filter the filter
	 * @return the entity
	 */
	Entity retrieveEntity(AcademicPublicationFilter filter);
	
	/**
	 * Retrieve entity.
	 *
	 * @param type the type
	 * @return the entity
	 */
	Entity retrieveEntity(String type);

	/**
	 * Filters chunk.
	 *
	 * @param filter the filter
	 * @return the string
	 */
	String filtersChunk(AcademicPublicationFilter filter);
	
	
	/**
	 * Filters chunk.
	 *
	 * @param id the id
	 * @return the string
	 */
	String filtersChunk(String id);
}
