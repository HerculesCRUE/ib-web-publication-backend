package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.ScientificPublicationDto;
import es.um.asio.service.mapper.decorator.ScientificPublicationMapperDecorator;

@Mapper
@DecoratedWith(ScientificPublicationMapperDecorator.class)
public interface ScientificPublicationMapper extends BaseMapper<ScientificPublicationDto> {
	
}
