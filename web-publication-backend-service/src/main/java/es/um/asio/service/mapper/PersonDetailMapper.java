package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.PersonDetailDto;
import es.um.asio.service.mapper.decorator.PersonDetailMapperDecorator;

@Mapper
@DecoratedWith(PersonDetailMapperDecorator.class)
public interface PersonDetailMapper extends BaseMapper<PersonDetailDto> {

}
