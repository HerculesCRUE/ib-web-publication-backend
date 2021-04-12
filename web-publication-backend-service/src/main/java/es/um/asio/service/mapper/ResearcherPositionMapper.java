package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.ResearcherPositionDto;
import es.um.asio.service.mapper.decorator.ResearcherPositionMapperDecorator;

@Mapper
@DecoratedWith(ResearcherPositionMapperDecorator.class)
public interface ResearcherPositionMapper extends BaseMapper<ResearcherPositionDto> {
	
}