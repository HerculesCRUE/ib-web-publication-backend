package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.GroupKeywordDto;
import es.um.asio.service.mapper.decorator.GroupKeywordMapperDecorator;

@Mapper
@DecoratedWith(GroupKeywordMapperDecorator.class)
public interface GroupKeywordMapper extends BaseMapper<GroupKeywordDto> {

}
