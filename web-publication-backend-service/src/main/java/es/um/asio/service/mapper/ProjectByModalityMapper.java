package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.ProjectByModalityDto;
import es.um.asio.service.mapper.decorator.ProjectByModalityMapperDecorator;

@Mapper
@DecoratedWith(ProjectByModalityMapperDecorator.class)
public interface ProjectByModalityMapper extends BaseMapper<ProjectByModalityDto> {

}
