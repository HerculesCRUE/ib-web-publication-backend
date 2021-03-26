package es.um.asio.service.proxy.articlekeyword.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.ArticleKeywordDto;
import es.um.asio.service.filter.articlekeyword.ArticleKeywordFilter;
import es.um.asio.service.mapper.ArticleKeywordMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.proxy.articlekeyword.ArticleKeywordProxy;
import es.um.asio.service.service.articlekeyword.ArticleKeywordService;

@Service
public class ArticleKeywordProxyImpl implements ArticleKeywordProxy {

	@Autowired
	private ArticleKeywordService service;
	
	@Autowired
	private ArticleKeywordMapper mapper;
	
	@Override
	public Page<ArticleKeywordDto> findPaginated(ArticleKeywordFilter filter, Pageable pageable) {
		Page<FusekiResponse> result = this.service.findPaginated(filter, pageable);
		
		return this.mapper.convertPageFusekiResponseToDto(result);
	}
}
