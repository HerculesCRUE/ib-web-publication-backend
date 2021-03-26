package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.MemberRoleDto;
import es.um.asio.service.mapper.decorator.MemberRoleMapperDecorator;

@Mapper
@DecoratedWith(MemberRoleMapperDecorator.class)
public interface MemberRoleMapper extends BaseMapper<MemberRoleDto> {

}
