package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.BookSectionKeywordDto;
import es.um.asio.service.mapper.decorator.BookSectionKeywordMapperDecorator;

@Mapper
@DecoratedWith(BookSectionKeywordMapperDecorator.class)
public interface BookSectionKeywordMapper extends BaseMapper<BookSectionKeywordDto> {

}
