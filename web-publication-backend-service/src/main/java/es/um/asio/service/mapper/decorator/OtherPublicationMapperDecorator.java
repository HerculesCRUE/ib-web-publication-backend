package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.OtherPublicationDto;
import es.um.asio.service.mapper.OtherPublicationMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class OtherPublicationMapperDecorator extends BaseMapperDecorator<OtherPublicationDto> implements OtherPublicationMapper {

	@Autowired
    @Qualifier("delegate")
	private OtherPublicationMapper mapper;
	
	public OtherPublicationMapperDecorator() {
		this.type = OtherPublicationDto.class;
	}
	
	@Override
	public OtherPublicationDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<OtherPublicationDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<OtherPublicationDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
