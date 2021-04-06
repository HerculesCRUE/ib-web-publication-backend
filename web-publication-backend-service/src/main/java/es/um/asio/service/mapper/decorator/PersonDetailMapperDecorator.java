package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.PersonDetailDto;
import es.um.asio.service.mapper.PersonDetailMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class PersonDetailMapperDecorator extends BaseMapperDecorator<PersonDetailDto> implements PersonDetailMapper {

	@Autowired
    @Qualifier("delegate")
	private PersonDetailMapper mapper;
	
	public PersonDetailMapperDecorator() {
		this.type = PersonDetailDto.class;
	}
	
	@Override
	public PersonDetailDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<PersonDetailDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<PersonDetailDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
