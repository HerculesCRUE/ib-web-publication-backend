package es.um.asio.service.proxy.booksectionkeyword.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.BookSectionKeywordDto;
import es.um.asio.service.filter.booksectionkeyword.BookSectionKeywordFilter;
import es.um.asio.service.mapper.BookSectionKeywordMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.proxy.booksectionkeyword.BookSectionKeywordProxy;
import es.um.asio.service.service.booksectionkeyword.BookSectionKeywordService;

@Service
public class BookSectionKeywordProxyImpl implements BookSectionKeywordProxy {

	@Autowired
	private BookSectionKeywordService service;
	
	@Autowired
	private BookSectionKeywordMapper mapper;
	
	@Override
	public Page<BookSectionKeywordDto> findPaginated(BookSectionKeywordFilter filter, Pageable pageable) {
		Page<FusekiResponse> result = this.service.findPaginated(filter, pageable);
		
		return this.mapper.convertPageFusekiResponseToDto(result);
	}
}
