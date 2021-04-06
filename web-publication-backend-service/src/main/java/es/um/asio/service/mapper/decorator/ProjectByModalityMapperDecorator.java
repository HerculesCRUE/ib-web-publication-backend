package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.ProjectByModalityDto;
import es.um.asio.service.mapper.ProjectByModalityMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class ProjectByModalityMapperDecorator extends BaseMapperDecorator<ProjectByModalityDto>
		implements ProjectByModalityMapper {

	@Autowired
	@Qualifier("delegate")
	private ProjectByModalityMapper mapper;

	public ProjectByModalityMapperDecorator() {
		this.type = ProjectByModalityDto.class;
	}

	@Override
	public List<ProjectByModalityDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public ProjectByModalityDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public PageImplHelper<ProjectByModalityDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}

}
