package es.um.asio.service.proxy.university;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.UniversityDto;
import es.um.asio.service.filter.university.UniversityFilter;

public interface UniversityProxy {

	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<UniversityDto>
	 */
	Page<UniversityDto> findPaginated(UniversityFilter filter, Pageable pageable);

	String getQualitySeal();

	String getFinancing();
}
