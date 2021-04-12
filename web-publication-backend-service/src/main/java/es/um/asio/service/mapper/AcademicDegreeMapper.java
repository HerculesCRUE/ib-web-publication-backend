package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.AcademicDegreeDto;
import es.um.asio.service.mapper.decorator.AcademicDegreeMapperDecorator;

@Mapper
@DecoratedWith(AcademicDegreeMapperDecorator.class)
public interface AcademicDegreeMapper extends BaseMapper<AcademicDegreeDto> {
	
}