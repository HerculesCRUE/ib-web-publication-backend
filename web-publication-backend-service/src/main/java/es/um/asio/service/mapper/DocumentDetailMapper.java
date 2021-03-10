package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.DocumentDetailDto;
import es.um.asio.service.mapper.decorator.DocumentDetailMapperDecorator;

@Mapper
@DecoratedWith(DocumentDetailMapperDecorator.class)
public interface DocumentDetailMapper extends BaseMapper<DocumentDetailDto> {

}
