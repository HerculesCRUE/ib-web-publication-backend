package es.um.asio.service.proxy.university;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.UniversityDto;
import es.um.asio.service.dto.graphic.GraphicsDto;
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

	List<GraphicsDto> organizationByType();
}
