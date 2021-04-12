package es.um.asio.service.proxy.academicdegree;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.AcademicDegreeDto;
import es.um.asio.service.filter.academicdegree.AcademicDegreeFilter;

public interface AcademicDegreeProxy {
	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<AcademicDegreeDto>
	 */
	Page<AcademicDegreeDto> findPaginated(AcademicDegreeFilter filter, Pageable pageable);
}
