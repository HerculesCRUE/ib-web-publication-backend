package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.FundingProgramDto;
import es.um.asio.service.mapper.FundingProgramMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class FundingProgramMapperDecorator extends BaseMapperDecorator<FundingProgramDto> implements FundingProgramMapper {

	@Autowired
    @Qualifier("delegate")
	private FundingProgramMapper mapper;
	
	public FundingProgramMapperDecorator() {
		this.type = FundingProgramDto.class;
	}
	
	@Override
	public FundingProgramDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<FundingProgramDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<FundingProgramDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
