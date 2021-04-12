package es.um.asio.service.proxy.principalinvestigatorrole;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.PrincipalInvestigatorRoleDto;
import es.um.asio.service.filter.principalinvestigatorrole.PrincipalInvestigatorRoleFilter;

public interface PrincipalInvestigatorRoleProxy {
	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<PrincipalInvestigatorRoleDto>
	 */
	Page<PrincipalInvestigatorRoleDto> findPaginated(PrincipalInvestigatorRoleFilter filter, Pageable pageable);
}
