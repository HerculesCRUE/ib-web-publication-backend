package es.um.asio.service.proxy.employmentcontract.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.EmploymentContractDto;
import es.um.asio.service.filter.employmentcontract.EmploymentContractFilter;
import es.um.asio.service.mapper.EmploymentContractMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.proxy.employmentcontract.EmploymentContractProxy;
import es.um.asio.service.service.employmentcontract.EmploymentContractService;

@Service
public class EmploymentContractProxyImpl implements EmploymentContractProxy {

	@Autowired
	private EmploymentContractService service;
	
	@Autowired
	private EmploymentContractMapper mapper;
	
	@Override
	public Page<EmploymentContractDto> findPaginated(EmploymentContractFilter filter, Pageable pageable) {
		Page<FusekiResponse> result = this.service.findPaginated(filter, pageable);
		
		return this.mapper.convertPageFusekiResponseToDto(result);
	}
}
