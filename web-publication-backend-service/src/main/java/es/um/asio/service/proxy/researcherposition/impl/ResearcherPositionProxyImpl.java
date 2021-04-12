package es.um.asio.service.proxy.researcherposition.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.ResearcherPositionDto;
import es.um.asio.service.filter.researcherposition.ResearcherPositionFilter;
import es.um.asio.service.mapper.ResearcherPositionMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.proxy.researcherposition.ResearcherPositionProxy;
import es.um.asio.service.service.researcherposition.ResearcherPositionService;

@Service
public class ResearcherPositionProxyImpl implements ResearcherPositionProxy {

	@Autowired
	private ResearcherPositionService service;
	
	@Autowired
	private ResearcherPositionMapper mapper;
	
	@Override
	public Page<ResearcherPositionDto> findPaginated(ResearcherPositionFilter filter, Pageable pageable) {
		Page<FusekiResponse> result = this.service.findPaginated(filter, pageable);
		
		return this.mapper.convertPageFusekiResponseToDto(result);
	}
}
