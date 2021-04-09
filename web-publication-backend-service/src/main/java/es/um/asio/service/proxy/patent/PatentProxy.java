package es.um.asio.service.proxy.patent;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.PatentDetailDto;
import es.um.asio.service.dto.PatentDto;
import es.um.asio.service.dto.graphic.PatentByOrganizationDto;
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

	PatentDetailDto find(String id);

	List<PatentByOrganizationDto> getbyOrganization();

}
