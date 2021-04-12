package es.um.asio.service.proxy.awardeddegree.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.AwardedDegreeDto;
import es.um.asio.service.filter.awardeddegree.AwardedDegreeFilter;
import es.um.asio.service.mapper.AwardedDegreeMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.proxy.awardeddegree.AwardedDegreeProxy;
import es.um.asio.service.service.awardeddegree.AwardedDegreeService;

@Service
public class AwardedDegreeProxyImpl implements AwardedDegreeProxy {

	@Autowired
	private AwardedDegreeService service;
	
	@Autowired
	private AwardedDegreeMapper mapper;
	
	@Override
	public Page<AwardedDegreeDto> findPaginated(AwardedDegreeFilter filter, Pageable pageable) {
		Page<FusekiResponse> result = this.service.findPaginated(filter, pageable);
		
		return this.mapper.convertPageFusekiResponseToDto(result);
	}
}
