package es.um.asio.service.proxy.curriculumvitae.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.CurriculumVitaeDto;
import es.um.asio.service.filter.curriculumvitae.CurriculumVitaeFilter;
import es.um.asio.service.mapper.CurriculumVitaeMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.proxy.curriculumvitae.CurriculumVitaeProxy;
import es.um.asio.service.service.curriculumvitae.CurriculumVitaeService;

@Service
public class CurriculumVitaeProxyImpl implements CurriculumVitaeProxy {

	@Autowired
	private CurriculumVitaeService service;
	
	@Autowired
	private CurriculumVitaeMapper mapper;
	
	@Override
	public Page<CurriculumVitaeDto> findPaginated(CurriculumVitaeFilter filter, Pageable pageable) {
		Page<FusekiResponse> result = this.service.findPaginated(filter, pageable);
		
		return this.mapper.convertPageFusekiResponseToDto(result);
	}
}
