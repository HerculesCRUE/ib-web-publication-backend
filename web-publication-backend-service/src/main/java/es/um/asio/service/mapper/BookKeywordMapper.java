package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.BookKeywordDto;
import es.um.asio.service.mapper.decorator.BookKeywordMapperDecorator;

@Mapper
@DecoratedWith(BookKeywordMapperDecorator.class)
public interface BookKeywordMapper extends BaseMapper<BookKeywordDto> {

}
