package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.BookKeywordDto;
import es.um.asio.service.mapper.BookKeywordMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class BookKeywordMapperDecorator extends BaseMapperDecorator<BookKeywordDto> implements BookKeywordMapper {

	@Autowired
    @Qualifier("delegate")
	private BookKeywordMapper mapper;
	
	public BookKeywordMapperDecorator() {
		this.type = BookKeywordDto.class;
	}
	
	@Override
	public BookKeywordDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<BookKeywordDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<BookKeywordDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
