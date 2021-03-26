package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.InternshipDto;
import es.um.asio.service.mapper.InternshipMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class InternshipMapperDecorator extends BaseMapperDecorator<InternshipDto> implements InternshipMapper {

	@Autowired
    @Qualifier("delegate")
	private InternshipMapper mapper;
	
	public InternshipMapperDecorator() {
		this.type = InternshipDto.class;
	}
	
	@Override
	public InternshipDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<InternshipDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<InternshipDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
