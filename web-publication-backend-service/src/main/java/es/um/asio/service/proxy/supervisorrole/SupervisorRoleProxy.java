package es.um.asio.service.proxy.supervisorrole;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.SupervisorRoleDto;
import es.um.asio.service.filter.supervisorrole.SupervisorRoleFilter;

public interface SupervisorRoleProxy {

	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<SupervisorRoleDto>
	 */
	Page<SupervisorRoleDto> findPaginated(SupervisorRoleFilter filter, Pageable pageable);
}
