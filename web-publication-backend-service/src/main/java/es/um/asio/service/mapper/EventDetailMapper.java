package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.EventDetailDto;
import es.um.asio.service.mapper.decorator.EventDetailMapperDecorator;

@Mapper
@DecoratedWith(EventDetailMapperDecorator.class)
public interface EventDetailMapper extends BaseMapper<EventDetailDto> {

}
