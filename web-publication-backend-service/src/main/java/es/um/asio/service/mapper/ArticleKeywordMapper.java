package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.ArticleKeywordDto;
import es.um.asio.service.mapper.decorator.ArticleKeywordMapperDecorator;

@Mapper
@DecoratedWith(ArticleKeywordMapperDecorator.class)
public interface ArticleKeywordMapper extends BaseMapper<ArticleKeywordDto> {

}
