package es.um.asio.service.proxy.awardeddegree;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.AwardedDegreeDto;
import es.um.asio.service.filter.awardeddegree.AwardedDegreeFilter;

public interface AwardedDegreeProxy {
	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<AwardedDegreeDto>
	 */
	Page<AwardedDegreeDto> findPaginated(AwardedDegreeFilter filter, Pageable pageable);
}
