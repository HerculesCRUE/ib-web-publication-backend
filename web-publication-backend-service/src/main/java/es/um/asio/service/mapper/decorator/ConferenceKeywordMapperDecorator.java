package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.ConferenceKeywordDto;
import es.um.asio.service.mapper.ConferenceKeywordMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class ConferenceKeywordMapperDecorator extends BaseMapperDecorator<ConferenceKeywordDto> implements ConferenceKeywordMapper {

	@Autowired
    @Qualifier("delegate")
	private ConferenceKeywordMapper mapper;
	
	public ConferenceKeywordMapperDecorator() {
		this.type = ConferenceKeywordDto.class;
	}
	
	@Override
	public ConferenceKeywordDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<ConferenceKeywordDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<ConferenceKeywordDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
