package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.PersonParticipantDto;
import es.um.asio.service.mapper.decorator.PersonParticipantMapperDecorator;

@Mapper
@DecoratedWith(PersonParticipantMapperDecorator.class)
public interface PersonParticipantMapper extends BaseMapper<PersonParticipantDto> {

}
