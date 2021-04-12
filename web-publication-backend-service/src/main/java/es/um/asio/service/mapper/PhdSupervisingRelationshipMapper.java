package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.PhdSupervisingRelationshipDto;
import es.um.asio.service.mapper.decorator.PhdSupervisingRelationshipMapperDecorator;

@Mapper
@DecoratedWith(PhdSupervisingRelationshipMapperDecorator.class)
public interface PhdSupervisingRelationshipMapper extends BaseMapper<PhdSupervisingRelationshipDto> {
	
}