package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.InternshipDto;
import es.um.asio.service.mapper.decorator.InternshipMapperDecorator;

@Mapper
@DecoratedWith(InternshipMapperDecorator.class)
public interface InternshipMapper extends BaseMapper<InternshipDto> {

}
