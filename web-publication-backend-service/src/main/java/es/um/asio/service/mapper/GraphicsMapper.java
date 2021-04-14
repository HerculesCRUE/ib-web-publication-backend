package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.graphic.GraphicsDto;
import es.um.asio.service.mapper.decorator.GraphicsMapperDecorator;

@Mapper
@DecoratedWith(GraphicsMapperDecorator.class)
public interface GraphicsMapper extends BaseMapper<GraphicsDto> {

}
