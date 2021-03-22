package es.um.asio.service.proxy.organization;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.OrganizationDetailDto;
import es.um.asio.service.dto.OrganizationDto;
import es.um.asio.service.filter.organization.OrganizationFilter;

public interface OrganizationProxy {

	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<OrganizationDto>
	 */
	Page<OrganizationDto> findPaginated(OrganizationFilter filter, Pageable pageable);

	/**
	 * Obtiene una organizacion
	 * 
	 * @param id
	 * @param type
	 * @return OrganizationDetailDto
	 */
	OrganizationDetailDto find(String id, String decodedString);
}
