package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.ProjectDetailDto;
import es.um.asio.service.dto.ProjectDto;
import es.um.asio.service.mapper.ProjectDetailMapper;
import es.um.asio.service.mapper.ProjectMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class ProjectDetailMapperDecorator extends BaseMapperDecorator<ProjectDetailDto> implements ProjectDetailMapper {

	@Autowired
    @Qualifier("delegate")
	private ProjectMapper mapper;
	
	public ProjectDetailMapperDecorator() {
		this.type = ProjectDetailDto.class;
	}
	
	@Override
	public ProjectDetailDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<ProjectDetailDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<ProjectDetailDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
