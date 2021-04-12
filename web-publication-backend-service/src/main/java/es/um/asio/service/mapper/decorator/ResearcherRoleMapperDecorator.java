package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.ResearcherRoleDto;
import es.um.asio.service.mapper.ResearcherRoleMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class ResearcherRoleMapperDecorator extends BaseMapperDecorator<ResearcherRoleDto> implements ResearcherRoleMapper {

	@Autowired
    @Qualifier("delegate")
	private ResearcherRoleMapper mapper;
	
	public ResearcherRoleMapperDecorator() {
		this.type = ResearcherRoleDto.class;
	}
	
	@Override
	public ResearcherRoleDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<ResearcherRoleDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<ResearcherRoleDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
