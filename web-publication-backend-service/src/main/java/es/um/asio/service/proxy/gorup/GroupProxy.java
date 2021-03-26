package es.um.asio.service.proxy.gorup;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.GroupDto;
import es.um.asio.service.filter.gorup.GroupFilter;

public interface GroupProxy {

	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<GroupDto>
	 */
	Page<GroupDto> findPaginated(GroupFilter filter, Pageable pageable);
}
