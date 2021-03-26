package es.um.asio.service.proxy.internship.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.InternshipDto;
import es.um.asio.service.filter.internship.InternshipFilter;
import es.um.asio.service.mapper.InternshipMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.proxy.internship.InternshipProxy;
import es.um.asio.service.service.internship.InternshipService;

@Service
public class InternshipProxyImpl implements InternshipProxy {

	@Autowired
	private InternshipService service;
	
	@Autowired
	private InternshipMapper mapper;
	
	@Override
	public Page<InternshipDto> findPaginated(InternshipFilter filter, Pageable pageable) {
		Page<FusekiResponse> result = this.service.findPaginated(filter, pageable);
		
		return this.mapper.convertPageFusekiResponseToDto(result);
	}
}
