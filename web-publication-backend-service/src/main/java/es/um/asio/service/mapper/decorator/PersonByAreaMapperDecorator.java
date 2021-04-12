package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.graphic.PersonByAreaDto;
import es.um.asio.service.mapper.PersonByAreaMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class PersonByAreaMapperDecorator extends BaseMapperDecorator<PersonByAreaDto> implements PersonByAreaMapper {

	@Autowired
	@Qualifier("delegate")
	private PersonByAreaMapper mapper;

	public PersonByAreaMapperDecorator() {
		this.type = PersonByAreaDto.class;
	}

	@Override
	public List<PersonByAreaDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PersonByAreaDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public PageImplHelper<PersonByAreaDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}

}
