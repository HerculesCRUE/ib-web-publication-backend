package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.OtherPublicationDto;
import es.um.asio.service.mapper.decorator.OtherPublicationMapperDecorator;

@Mapper
@DecoratedWith(OtherPublicationMapperDecorator.class)
public interface OtherPublicationMapper extends BaseMapper<OtherPublicationDto> {

}
