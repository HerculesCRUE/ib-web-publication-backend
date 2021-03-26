package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.ResearchFieldDto;
import es.um.asio.service.mapper.decorator.ResearchFieldMapperDecorator;

@Mapper
@DecoratedWith(ResearchFieldMapperDecorator.class)
public interface ResearchFieldMapper extends BaseMapper<ResearchFieldDto> {

}
