package es.um.asio.service.proxy.principalinvestigatorrole.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.PrincipalInvestigatorRoleDto;
import es.um.asio.service.filter.principalinvestigatorrole.PrincipalInvestigatorRoleFilter;
import es.um.asio.service.mapper.PrincipalInvestigatorRoleMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.proxy.principalinvestigatorrole.PrincipalInvestigatorRoleProxy;
import es.um.asio.service.service.principalinvestigatorrole.PrincipalInvestigatorRoleService;

@Service
public class PrincipalInvestigatorRoleProxyImpl implements PrincipalInvestigatorRoleProxy {

	@Autowired
	private PrincipalInvestigatorRoleService service;
	
	@Autowired
	private PrincipalInvestigatorRoleMapper mapper;
	
	@Override
	public Page<PrincipalInvestigatorRoleDto> findPaginated(PrincipalInvestigatorRoleFilter filter, Pageable pageable) {
		Page<FusekiResponse> result = this.service.findPaginated(filter, pageable);
		
		return this.mapper.convertPageFusekiResponseToDto(result);
	}
}
