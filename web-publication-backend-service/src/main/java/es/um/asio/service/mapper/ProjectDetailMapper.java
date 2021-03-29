package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.ProjectDetailDto;
import es.um.asio.service.dto.ProjectDto;
import es.um.asio.service.mapper.decorator.ProjectDetailMapperDecorator;
import es.um.asio.service.mapper.decorator.ProjectMapperDecorator;

@Mapper
@DecoratedWith(ProjectDetailMapperDecorator.class)
public interface ProjectDetailMapper extends BaseMapper<ProjectDetailDto> {

}
