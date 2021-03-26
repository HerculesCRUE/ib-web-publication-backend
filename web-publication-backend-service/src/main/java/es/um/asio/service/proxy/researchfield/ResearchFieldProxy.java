package es.um.asio.service.proxy.researchfield;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.ResearchFieldDto;
import es.um.asio.service.filter.researchfield.ResearchFieldFilter;

public interface ResearchFieldProxy {

	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<ResearchFieldDto>
	 */
	Page<ResearchFieldDto> findPaginated(ResearchFieldFilter filter, Pageable pageable);
}
