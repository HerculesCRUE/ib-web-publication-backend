package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.AcademicDegreeDto;
import es.um.asio.service.mapper.AcademicDegreeMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class AcademicDegreeMapperDecorator extends BaseMapperDecorator<AcademicDegreeDto> implements AcademicDegreeMapper {

	@Autowired
    @Qualifier("delegate")
	private AcademicDegreeMapper mapper;
	
	public AcademicDegreeMapperDecorator() {
		this.type = AcademicDegreeDto.class;
	}
	
	@Override
	public AcademicDegreeDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<AcademicDegreeDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<AcademicDegreeDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
