package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.graphic.PersonByAreaDto;
import es.um.asio.service.mapper.decorator.PersonByAreaMapperDecorator;

@Mapper
@DecoratedWith(PersonByAreaMapperDecorator.class)
public interface PersonByAreaMapper extends BaseMapper<PersonByAreaDto> {

}
