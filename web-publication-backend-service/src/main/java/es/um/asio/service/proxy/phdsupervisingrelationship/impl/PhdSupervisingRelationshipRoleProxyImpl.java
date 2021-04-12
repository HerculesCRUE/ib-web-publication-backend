package es.um.asio.service.proxy.phdsupervisingrelationship.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.PhdSupervisingRelationshipDto;
import es.um.asio.service.filter.phdsupervisingrelationship.PhdSupervisingRelationshipFilter;
import es.um.asio.service.mapper.PhdSupervisingRelationshipMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.proxy.phdsupervisingrelationship.PhdSupervisingRelationshipProxy;
import es.um.asio.service.service.phdsupervisingrelationship.PhdSupervisingRelationshipService;

@Service
public class PhdSupervisingRelationshipRoleProxyImpl implements PhdSupervisingRelationshipProxy {

	@Autowired
	private PhdSupervisingRelationshipService service;
	
	@Autowired
	private PhdSupervisingRelationshipMapper mapper;
	
	@Override
	public Page<PhdSupervisingRelationshipDto> findPaginated(PhdSupervisingRelationshipFilter filter, Pageable pageable) {
		Page<FusekiResponse> result = this.service.findPaginated(filter, pageable);
		
		return this.mapper.convertPageFusekiResponseToDto(result);
	}
}
