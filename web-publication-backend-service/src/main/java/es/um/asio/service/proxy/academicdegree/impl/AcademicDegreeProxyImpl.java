package es.um.asio.service.proxy.academicdegree.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.AcademicDegreeDto;
import es.um.asio.service.filter.academicdegree.AcademicDegreeFilter;
import es.um.asio.service.mapper.AcademicDegreeMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.proxy.academicdegree.AcademicDegreeProxy;
import es.um.asio.service.service.academicdegree.AcademicDegreeService;

@Service
public class AcademicDegreeProxyImpl implements AcademicDegreeProxy {

	@Autowired
	private AcademicDegreeService service;
	
	@Autowired
	private AcademicDegreeMapper mapper;
	
	@Override
	public Page<AcademicDegreeDto> findPaginated(AcademicDegreeFilter filter, Pageable pageable) {
		Page<FusekiResponse> result = this.service.findPaginated(filter, pageable);
		
		return this.mapper.convertPageFusekiResponseToDto(result);
	}
}
