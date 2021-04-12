package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.AcademicPublicationDetailDto;
import es.um.asio.service.dto.AcademicPublicationDto;
import es.um.asio.service.mapper.AcademicPublicationDetailMapper;
import es.um.asio.service.mapper.AcademicPublicationMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class AcademicPublicationDetailMapperDecorator extends BaseMapperDecorator<AcademicPublicationDetailDto> implements AcademicPublicationDetailMapper {

	@Autowired
    @Qualifier("delegate")
	private AcademicPublicationMapper mapper;
	
	public AcademicPublicationDetailMapperDecorator() {
		this.type = AcademicPublicationDetailDto.class;
	}
	
	@Override
	public AcademicPublicationDetailDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<AcademicPublicationDetailDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<AcademicPublicationDetailDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
