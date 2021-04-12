package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.AwardedDegreeDto;
import es.um.asio.service.mapper.AwardedDegreeMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class AwardedDegreeMapperDecorator extends BaseMapperDecorator<AwardedDegreeDto> implements AwardedDegreeMapper {

	@Autowired
    @Qualifier("delegate")
	private AwardedDegreeMapper mapper;
	
	public AwardedDegreeMapperDecorator() {
		this.type = AwardedDegreeDto.class;
	}
	
	@Override
	public AwardedDegreeDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<AwardedDegreeDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<AwardedDegreeDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
