package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.ConferenceKeywordDto;
import es.um.asio.service.mapper.decorator.ConferenceKeywordMapperDecorator;

@Mapper
@DecoratedWith(ConferenceKeywordMapperDecorator.class)
public interface ConferenceKeywordMapper extends BaseMapper<ConferenceKeywordDto> {

}
