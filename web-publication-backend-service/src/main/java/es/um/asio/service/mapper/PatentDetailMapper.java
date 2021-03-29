package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.PatentDetailDto;
import es.um.asio.service.mapper.decorator.PatentDetailMapperDecorator;

@Mapper
@DecoratedWith(PatentDetailMapperDecorator.class)
public interface PatentDetailMapper extends BaseMapper<PatentDetailDto> {

}
