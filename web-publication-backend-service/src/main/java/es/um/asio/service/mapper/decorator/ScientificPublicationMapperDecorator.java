package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.ScientificPublicationDto;
import es.um.asio.service.mapper.ScientificPublicationMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class ScientificPublicationMapperDecorator extends BaseMapperDecorator<ScientificPublicationDto> implements ScientificPublicationMapper {

	@Autowired
    @Qualifier("delegate")
	private ScientificPublicationMapper mapper;
	
	public ScientificPublicationMapperDecorator() {
		this.type = ScientificPublicationDto.class;
	}
	
	@Override
	public ScientificPublicationDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<ScientificPublicationDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<ScientificPublicationDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
