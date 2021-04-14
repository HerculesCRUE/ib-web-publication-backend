package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.PersonParticipantDto;
import es.um.asio.service.mapper.PersonParticipantMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class PersonParticipantMapperDecorator extends BaseMapperDecorator<PersonParticipantDto> implements PersonParticipantMapper {

	@Autowired
    @Qualifier("delegate")
	private PersonParticipantMapper mapper;
	
	public PersonParticipantMapperDecorator() {
		this.type = PersonParticipantDto.class;
	}
	
	@Override
	public PersonParticipantDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<PersonParticipantDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<PersonParticipantDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
