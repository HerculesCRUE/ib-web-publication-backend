package es.um.asio.service.proxy.memberrole;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.MemberRoleDto;
import es.um.asio.service.filter.memberrole.MemberRoleFilter;

public interface MemberRolePoxy {

	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<MemberRoleDto>
	 */
	Page<MemberRoleDto> findPaginated(MemberRoleFilter filter, Pageable pageable);
}
