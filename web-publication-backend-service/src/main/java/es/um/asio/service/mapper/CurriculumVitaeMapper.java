package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.CurriculumVitaeDto;
import es.um.asio.service.mapper.decorator.CurriculumVitaeMapperDecorator;

@Mapper
@DecoratedWith(CurriculumVitaeMapperDecorator.class)
public interface CurriculumVitaeMapper extends BaseMapper<CurriculumVitaeDto> {
	
}