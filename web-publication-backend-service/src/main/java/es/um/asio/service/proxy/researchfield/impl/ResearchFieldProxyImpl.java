package es.um.asio.service.proxy.researchfield.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.ResearchFieldDto;
import es.um.asio.service.filter.researchfield.ResearchFieldFilter;
import es.um.asio.service.mapper.ResearchFieldMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.proxy.researchfield.ResearchFieldProxy;
import es.um.asio.service.service.researchfield.ResearchFieldService;

@Service
public class ResearchFieldProxyImpl implements ResearchFieldProxy {

	@Autowired
	private ResearchFieldService service;
	
	@Autowired
	private ResearchFieldMapper mapper;
	
	@Override
	public Page<ResearchFieldDto> findPaginated(ResearchFieldFilter filter, Pageable pageable) {
		Page<FusekiResponse> result = this.service.findPaginated(filter, pageable);
		
		return this.mapper.convertPageFusekiResponseToDto(result);
	}
}
