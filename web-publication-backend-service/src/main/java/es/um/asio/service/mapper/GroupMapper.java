package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.GroupDto;
import es.um.asio.service.mapper.decorator.GroupMapperDecorator;

@Mapper
@DecoratedWith(GroupMapperDecorator.class)
public interface GroupMapper extends BaseMapper<GroupDto> {

}
