package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.graphic.PatentByOrganizationDto;
import es.um.asio.service.mapper.decorator.PatentByOrganizationMapperDecorator;

@Mapper
@DecoratedWith(PatentByOrganizationMapperDecorator.class)
public interface PatentByOrganizationMapper extends BaseMapper<PatentByOrganizationDto> {

}
