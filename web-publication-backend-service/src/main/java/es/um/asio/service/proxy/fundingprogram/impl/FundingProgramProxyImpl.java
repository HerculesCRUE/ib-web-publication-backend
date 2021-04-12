package es.um.asio.service.proxy.fundingprogram.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.FundingProgramDto;
import es.um.asio.service.filter.fundingprogram.FundingProgramFilter;
import es.um.asio.service.mapper.FundingProgramMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.proxy.fundingprogram.FundingProgramProxy;
import es.um.asio.service.service.fundingprogram.FundingProgramService;

@Service
public class FundingProgramProxyImpl implements FundingProgramProxy {

	@Autowired
	private FundingProgramService service;
	
	@Autowired
	private FundingProgramMapper mapper;
	
	@Override
	public Page<FundingProgramDto> findPaginated(FundingProgramFilter filter, Pageable pageable) {
		Page<FusekiResponse> result = this.service.findPaginated(filter, pageable);
		
		return this.mapper.convertPageFusekiResponseToDto(result);
	}
}
