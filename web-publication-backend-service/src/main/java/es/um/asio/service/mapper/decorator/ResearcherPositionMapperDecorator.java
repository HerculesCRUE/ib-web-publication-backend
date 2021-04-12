package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.ResearcherPositionDto;
import es.um.asio.service.mapper.ResearcherPositionMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class ResearcherPositionMapperDecorator extends BaseMapperDecorator<ResearcherPositionDto> implements ResearcherPositionMapper {

	@Autowired
    @Qualifier("delegate")
	private ResearcherPositionMapper mapper;
	
	public ResearcherPositionMapperDecorator() {
		this.type = ResearcherPositionDto.class;
	}
	
	@Override
	public ResearcherPositionDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<ResearcherPositionDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<ResearcherPositionDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
