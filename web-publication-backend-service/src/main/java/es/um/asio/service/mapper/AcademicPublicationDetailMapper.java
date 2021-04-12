package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.AcademicPublicationDetailDto;
import es.um.asio.service.dto.AcademicPublicationDto;
import es.um.asio.service.mapper.decorator.AcademicPublicationDetailMapperDecorator;
import es.um.asio.service.mapper.decorator.AcademicPublicationMapperDecorator;

@Mapper
@DecoratedWith(AcademicPublicationDetailMapperDecorator.class)
public interface AcademicPublicationDetailMapper extends BaseMapper<AcademicPublicationDetailDto> {

}
