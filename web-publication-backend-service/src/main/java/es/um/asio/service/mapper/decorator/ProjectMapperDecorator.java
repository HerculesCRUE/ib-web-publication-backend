package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.ProjectDto;
import es.um.asio.service.mapper.ProjectMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class ProjectMapperDecorator extends BaseMapperDecorator<ProjectDto> implements ProjectMapper {

	@Autowired
	@Qualifier("delegate")
	private ProjectMapper mapper;

	public ProjectMapperDecorator() {
		this.type = ProjectDto.class;
	}

	@Override
	public ProjectDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<ProjectDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<ProjectDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
