package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.ResearchFieldDto;
import es.um.asio.service.mapper.ResearchFieldMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class ResearchFieldMapperDecorator extends BaseMapperDecorator<ResearchFieldDto> implements ResearchFieldMapper {

	@Autowired
    @Qualifier("delegate")
	private ResearchFieldMapper mapper;
	
	public ResearchFieldMapperDecorator() {
		this.type = ResearchFieldDto.class;
	}
	
	@Override
	public ResearchFieldDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<ResearchFieldDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<ResearchFieldDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
