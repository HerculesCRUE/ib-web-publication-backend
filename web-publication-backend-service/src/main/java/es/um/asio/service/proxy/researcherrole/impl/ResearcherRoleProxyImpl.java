package es.um.asio.service.proxy.researcherrole.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.ResearcherRoleDto;
import es.um.asio.service.filter.researcherrole.ResearcherRoleFilter;
import es.um.asio.service.mapper.ResearcherRoleMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.proxy.researcherrole.ResearcherRoleProxy;
import es.um.asio.service.service.researcherrole.ResearcherRoleService;

@Service
public class ResearcherRoleProxyImpl implements ResearcherRoleProxy {

	@Autowired
	private ResearcherRoleService service;
	
	@Autowired
	private ResearcherRoleMapper mapper;
	
	@Override
	public Page<ResearcherRoleDto> findPaginated(ResearcherRoleFilter filter, Pageable pageable) {
		Page<FusekiResponse> result = this.service.findPaginated(filter, pageable);
		
		return this.mapper.convertPageFusekiResponseToDto(result);
	}
}
