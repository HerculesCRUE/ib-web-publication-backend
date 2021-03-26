package es.um.asio.service.proxy.bookkeyword.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.BookKeywordDto;
import es.um.asio.service.filter.bookkeyword.BookKeywordFilter;
import es.um.asio.service.mapper.BookKeywordMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.proxy.bookkeyword.BookKeywordProxy;
import es.um.asio.service.service.bookkeyword.BookKeywordService;

@Service
public class BookKeywordProxyImpl implements BookKeywordProxy {

	@Autowired
	private BookKeywordService service;
	
	@Autowired
	private BookKeywordMapper mapper;
	
	@Override
	public Page<BookKeywordDto> findPaginated(BookKeywordFilter filter, Pageable pageable) {
		Page<FusekiResponse> result = this.service.findPaginated(filter, pageable);
		
		return this.mapper.convertPageFusekiResponseToDto(result);
	}
}
