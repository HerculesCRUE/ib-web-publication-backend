package es.um.asio.service.proxy.memberrole.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.MemberRoleDto;
import es.um.asio.service.filter.memberrole.MemberRoleFilter;
import es.um.asio.service.mapper.MemberRoleMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.proxy.memberrole.MemberRolePoxy;
import es.um.asio.service.service.memberrole.MemberRoleService;

@Service
public class MemberRoleProxyImpl implements MemberRolePoxy {

	@Autowired
	private MemberRoleService service;
	
	@Autowired
	private MemberRoleMapper mapper;
	
	@Override
	public Page<MemberRoleDto> findPaginated(MemberRoleFilter filter, Pageable pageable) {
		Page<FusekiResponse> result = this.service.findPaginated(filter, pageable);
		
		return this.mapper.convertPageFusekiResponseToDto(result);
	}
}
