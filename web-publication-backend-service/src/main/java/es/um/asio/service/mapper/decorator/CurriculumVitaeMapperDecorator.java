package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.CurriculumVitaeDto;
import es.um.asio.service.mapper.CurriculumVitaeMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class CurriculumVitaeMapperDecorator extends BaseMapperDecorator<CurriculumVitaeDto> implements CurriculumVitaeMapper {

	@Autowired
    @Qualifier("delegate")
	private CurriculumVitaeMapper mapper;
	
	public CurriculumVitaeMapperDecorator() {
		this.type = CurriculumVitaeDto.class;
	}
	
	@Override
	public CurriculumVitaeDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<CurriculumVitaeDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<CurriculumVitaeDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
