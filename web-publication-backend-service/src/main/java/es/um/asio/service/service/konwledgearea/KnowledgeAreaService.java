package es.um.asio.service.service.konwledgearea;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.filter.knowledgearea.KnowledgeAreaFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;

public interface KnowledgeAreaService {

	/**
	 * Find paginated.
	 *
	 * @param filter   the filter
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<FusekiResponse> findPaginated(KnowledgeAreaFilter filter, Pageable pageable);
	
	List<Object> findAll();

	/**
	 * Retrieve entity.
	 *
	 * @return the entity
	 */
	Entity retrieveEntity();

	String filtersChunk(KnowledgeAreaFilter filter);
}
