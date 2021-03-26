package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.SupervisorRoleDto;
import es.um.asio.service.mapper.SupervisorRoleMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class SupervisorRoleMapperDecorator extends BaseMapperDecorator<SupervisorRoleDto> implements SupervisorRoleMapper {

	@Autowired
    @Qualifier("delegate")
	private SupervisorRoleMapper mapper;
	
	public SupervisorRoleMapperDecorator() {
		this.type = SupervisorRoleDto.class;
	}
	
	@Override
	public SupervisorRoleDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<SupervisorRoleDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<SupervisorRoleDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
