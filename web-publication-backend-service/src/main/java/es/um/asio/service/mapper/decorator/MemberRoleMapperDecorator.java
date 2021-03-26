package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.MemberRoleDto;
import es.um.asio.service.mapper.MemberRoleMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class MemberRoleMapperDecorator extends BaseMapperDecorator<MemberRoleDto> implements MemberRoleMapper {

	@Autowired
    @Qualifier("delegate")
	private MemberRoleMapper mapper;
	
	public MemberRoleMapperDecorator() {
		this.type = MemberRoleDto.class;
	}
	
	@Override
	public MemberRoleDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<MemberRoleDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<MemberRoleDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
