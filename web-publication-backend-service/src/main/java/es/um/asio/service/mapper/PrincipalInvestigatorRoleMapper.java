package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.PrincipalInvestigatorRoleDto;
import es.um.asio.service.mapper.decorator.PrincipalInvestigatorRoleMapperDecorator;

@Mapper
@DecoratedWith(PrincipalInvestigatorRoleMapperDecorator.class)
public interface PrincipalInvestigatorRoleMapper extends BaseMapper<PrincipalInvestigatorRoleDto> {
	
}