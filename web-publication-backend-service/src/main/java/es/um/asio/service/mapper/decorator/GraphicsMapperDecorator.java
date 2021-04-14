package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.graphic.GraphicsDto;
import es.um.asio.service.mapper.GraphicsMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class GraphicsMapperDecorator extends BaseMapperDecorator<GraphicsDto>
		implements GraphicsMapper {

	@Autowired
	@Qualifier("delegate")
	private GraphicsMapper mapper;

	public GraphicsMapperDecorator() {
		this.type = GraphicsDto.class;
	}

	@Override
	public List<GraphicsDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public GraphicsDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public PageImplHelper<GraphicsDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}

}
