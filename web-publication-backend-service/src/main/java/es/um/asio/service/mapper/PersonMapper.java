package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.PersonDto;
import es.um.asio.service.mapper.decorator.PersonMapperDecorator;

@Mapper
@DecoratedWith(PersonMapperDecorator.class)
public interface PersonMapper extends BaseMapper<PersonDto> {
	

}
