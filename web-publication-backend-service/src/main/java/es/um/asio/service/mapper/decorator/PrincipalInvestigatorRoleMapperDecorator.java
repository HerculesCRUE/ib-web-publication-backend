package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.PrincipalInvestigatorRoleDto;
import es.um.asio.service.mapper.PrincipalInvestigatorRoleMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class PrincipalInvestigatorRoleMapperDecorator extends BaseMapperDecorator<PrincipalInvestigatorRoleDto> implements PrincipalInvestigatorRoleMapper {

	@Autowired
    @Qualifier("delegate")
	private PrincipalInvestigatorRoleMapper mapper;
	
	public PrincipalInvestigatorRoleMapperDecorator() {
		this.type = PrincipalInvestigatorRoleDto.class;
	}
	
	@Override
	public PrincipalInvestigatorRoleDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<PrincipalInvestigatorRoleDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<PrincipalInvestigatorRoleDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
