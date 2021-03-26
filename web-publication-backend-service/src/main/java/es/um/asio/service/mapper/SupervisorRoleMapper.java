package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.SupervisorRoleDto;
import es.um.asio.service.mapper.decorator.SupervisorRoleMapperDecorator;

@Mapper
@DecoratedWith(SupervisorRoleMapperDecorator.class)
public interface SupervisorRoleMapper extends BaseMapper<SupervisorRoleDto> {

}
