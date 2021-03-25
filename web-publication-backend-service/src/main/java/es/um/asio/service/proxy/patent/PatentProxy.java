package es.um.asio.service.proxy.patent;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.PatentDto;
import es.um.asio.service.filter.patent.PatentFilter;

/**
 * Interfaz Proxy Patente
 *
 */
public interface PatentProxy {

	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<PatentDto>
	 */
	Page<PatentDto> findPaginated(PatentFilter filter, Pageable pageable);

	String getArea();

}
