package es.um.asio.service.proxy.gorup.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.GroupDto;
import es.um.asio.service.filter.gorup.GroupFilter;
import es.um.asio.service.mapper.GroupMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.proxy.gorup.GroupProxy;
import es.um.asio.service.service.group.GroupService;

@Service
public class GroupProxyImp implements GroupProxy {

	@Autowired
	private GroupService service;
	
	@Autowired
	private GroupMapper mapper;
	
	@Override
	public Page<GroupDto> findPaginated(GroupFilter filter, Pageable pageable) {
		Page<FusekiResponse> result = this.service.findPaginated(filter, pageable);
		
		return this.mapper.convertPageFusekiResponseToDto(result);
	}
}
