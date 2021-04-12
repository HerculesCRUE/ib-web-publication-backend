package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.ResearcherRoleDto;
import es.um.asio.service.mapper.decorator.ResearcherRoleMapperDecorator;

@Mapper
@DecoratedWith(ResearcherRoleMapperDecorator.class)
public interface ResearcherRoleMapper extends BaseMapper<ResearcherRoleDto> {
	
}