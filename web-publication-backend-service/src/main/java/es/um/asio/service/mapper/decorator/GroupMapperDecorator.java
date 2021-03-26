package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.GroupDto;
import es.um.asio.service.mapper.GroupMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class GroupMapperDecorator extends BaseMapperDecorator<GroupDto> implements GroupMapper {

	@Autowired
    @Qualifier("delegate")
	private GroupMapper mapper;
	
	public GroupMapperDecorator() {
		this.type = GroupDto.class;
	}
	
	@Override
	public GroupDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<GroupDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<GroupDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
