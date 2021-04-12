package es.um.asio.service.proxy.researcherposition;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.ResearcherPositionDto;
import es.um.asio.service.filter.researcherposition.ResearcherPositionFilter;

public interface ResearcherPositionProxy {
	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<ResearcherPositionDto>
	 */
	Page<ResearcherPositionDto> findPaginated(ResearcherPositionFilter filter, Pageable pageable);
}
