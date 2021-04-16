package es.um.asio.service.proxy.knowledgearea.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.KnowledgeAreaDto;
import es.um.asio.service.filter.knowledgearea.KnowledgeAreaFilter;
import es.um.asio.service.mapper.KnowledgeAreaMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.proxy.knowledgearea.KnowledgeAreaProxy;
import es.um.asio.service.service.konwledgearea.KnowledgeAreaService;

@Service
public class KnowledgeAreaProxyImpl implements KnowledgeAreaProxy {

	@Autowired
	private KnowledgeAreaService service;
	
	@Autowired
	private KnowledgeAreaMapper mapper;
	
	@Override
	public Page<KnowledgeAreaDto> findPaginated(KnowledgeAreaFilter filter, Pageable pageable) {
		Page<FusekiResponse> result = this.service.findPaginated(filter, pageable);
		
		return this.mapper.convertPageFusekiResponseToDto(result);
	}

	@Override
	public List<KnowledgeAreaDto> findAll() {
		return this.mapper.convertFusekiResponseToDto(this.service.findAll());
	}
}
