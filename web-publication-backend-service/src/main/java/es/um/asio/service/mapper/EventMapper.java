package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.EventDto;
import es.um.asio.service.mapper.decorator.EventMapperDecorator;


@Mapper
@DecoratedWith(EventMapperDecorator.class)
public interface EventMapper extends BaseMapper<EventDto> {

}
