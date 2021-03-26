package es.um.asio.service.proxy.conferencekeyword.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.ConferenceKeywordDto;
import es.um.asio.service.filter.conferencekeyword.ConferenceKeywordFilter;
import es.um.asio.service.mapper.ConferenceKeywordMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.proxy.conferencekeyword.ConferenceKeywordProxy;
import es.um.asio.service.service.conferencekeyword.ConferenceKeywordService;

@Service
public class ConferenceKeywordProxyImpl implements ConferenceKeywordProxy {

	@Autowired
	private ConferenceKeywordService service;
	
	@Autowired
	private ConferenceKeywordMapper mapper;
	
	@Override
	public Page<ConferenceKeywordDto> findPaginated(ConferenceKeywordFilter filter, Pageable pageable) {
		Page<FusekiResponse> result = this.service.findPaginated(filter, pageable);
		
		return this.mapper.convertPageFusekiResponseToDto(result);
	}
}
