package es.um.asio.service.proxy.supervisorrole.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.SupervisorRoleDto;
import es.um.asio.service.filter.supervisorrole.SupervisorRoleFilter;
import es.um.asio.service.mapper.SupervisorRoleMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.proxy.supervisorrole.SupervisorRoleProxy;
import es.um.asio.service.service.supervisorrole.SupervisorRoleService;

@Service
public class SupervisorRoleProxyImpl implements SupervisorRoleProxy {

	@Autowired
	private SupervisorRoleService service;
	
	@Autowired
	private SupervisorRoleMapper mapper;
	
	@Override
	public Page<SupervisorRoleDto> findPaginated(SupervisorRoleFilter filter, Pageable pageable) {
		Page<FusekiResponse> result = this.service.findPaginated(filter, pageable);
		
		return this.mapper.convertPageFusekiResponseToDto(result);
	}
}
