package es.um.asio.service.proxy.gorupkeyword.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.GroupKeywordDto;
import es.um.asio.service.filter.groupkeyword.GroupKeywordFilter;
import es.um.asio.service.mapper.GroupKeywordMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.proxy.gorupkeyword.GroupKeywordProxy;
import es.um.asio.service.service.groupkeyword.GroupKeywordService;

@Service
public class GroupKeywordProxyImpl implements GroupKeywordProxy {

	@Autowired
	private GroupKeywordService service;
	
	@Autowired
	private GroupKeywordMapper mapper;
	
	@Override
	public Page<GroupKeywordDto> findPaginated(GroupKeywordFilter filter, Pageable pageable) {
		Page<FusekiResponse> result = this.service.findPaginated(filter, pageable);
		
		return this.mapper.convertPageFusekiResponseToDto(result);
	}
}
