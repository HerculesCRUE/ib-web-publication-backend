package es.um.asio.service.proxy.curriculumvitae;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.CurriculumVitaeDto;
import es.um.asio.service.filter.curriculumvitae.CurriculumVitaeFilter;

public interface CurriculumVitaeProxy {
	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<CurriculumVitaeDto>
	 */
	Page<CurriculumVitaeDto> findPaginated(CurriculumVitaeFilter filter, Pageable pageable);
}
