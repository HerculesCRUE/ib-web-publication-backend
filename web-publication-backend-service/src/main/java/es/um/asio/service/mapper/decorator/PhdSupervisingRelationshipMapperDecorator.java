package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.PhdSupervisingRelationshipDto;
import es.um.asio.service.mapper.PhdSupervisingRelationshipMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class PhdSupervisingRelationshipMapperDecorator extends BaseMapperDecorator<PhdSupervisingRelationshipDto> implements PhdSupervisingRelationshipMapper {

	@Autowired
    @Qualifier("delegate")
	private PhdSupervisingRelationshipMapper mapper;
	
	public PhdSupervisingRelationshipMapperDecorator() {
		this.type = PhdSupervisingRelationshipDto.class;
	}
	
	@Override
	public PhdSupervisingRelationshipDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<PhdSupervisingRelationshipDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<PhdSupervisingRelationshipDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
