package es.um.asio.service.proxy.internship;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.InternshipDto;
import es.um.asio.service.filter.internship.InternshipFilter;

public interface InternshipProxy {

	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<InternshipDto>
	 */
	Page<InternshipDto> findPaginated(InternshipFilter filter, Pageable pageable);
}
