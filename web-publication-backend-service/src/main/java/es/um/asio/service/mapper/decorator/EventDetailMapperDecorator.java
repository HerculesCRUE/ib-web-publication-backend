package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.EventDetailDto;
import es.um.asio.service.mapper.EventDetailMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class EventDetailMapperDecorator extends BaseMapperDecorator<EventDetailDto> implements EventDetailMapper {

	@Autowired
    @Qualifier("delegate")
	private EventDetailMapper mapper;
	
	public EventDetailMapperDecorator() {
		this.type = EventDetailDto.class;
	}
	
	@Override
	public EventDetailDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<EventDetailDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<EventDetailDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
