package es.um.asio.service.proxy.researcherrole;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.ResearcherRoleDto;
import es.um.asio.service.filter.researcherrole.ResearcherRoleFilter;

public interface ResearcherRoleProxy {
	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<ResearcherRoleDto>
	 */
	Page<ResearcherRoleDto> findPaginated(ResearcherRoleFilter filter, Pageable pageable);
}
