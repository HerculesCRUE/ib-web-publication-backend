package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.GroupKeywordDto;
import es.um.asio.service.mapper.GroupKeywordMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class GroupKeywordMapperDecorator extends BaseMapperDecorator<GroupKeywordDto> implements GroupKeywordMapper {

	@Autowired
    @Qualifier("delegate")
	private GroupKeywordMapper mapper;
	
	public GroupKeywordMapperDecorator() {
		this.type = GroupKeywordDto.class;
	}
	
	@Override
	public GroupKeywordDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<GroupKeywordDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<GroupKeywordDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
