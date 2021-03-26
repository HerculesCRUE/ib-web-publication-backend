package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.ArticleKeywordDto;
import es.um.asio.service.mapper.ArticleKeywordMapper;
import es.um.asio.service.mapper.ArticleMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class ArticleKeywordMapperDecorator extends BaseMapperDecorator<ArticleKeywordDto> implements ArticleKeywordMapper {

	@Autowired
    @Qualifier("delegate")
	private ArticleMapper mapper;
	
	public ArticleKeywordMapperDecorator() {
		this.type = ArticleKeywordDto.class;
	}
	
	@Override
	public ArticleKeywordDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<ArticleKeywordDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<ArticleKeywordDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
