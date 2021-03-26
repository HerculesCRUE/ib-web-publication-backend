package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.BookSectionKeywordDto;
import es.um.asio.service.mapper.BookSectionKeywordMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class BookSectionKeywordMapperDecorator extends BaseMapperDecorator<BookSectionKeywordDto> implements BookSectionKeywordMapper {

	@Autowired
    @Qualifier("delegate")
	private BookSectionKeywordMapper mapper;
	
	public BookSectionKeywordMapperDecorator() {
		this.type = BookSectionKeywordDto.class;
	}
	
	@Override
	public BookSectionKeywordDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<BookSectionKeywordDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<BookSectionKeywordDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
