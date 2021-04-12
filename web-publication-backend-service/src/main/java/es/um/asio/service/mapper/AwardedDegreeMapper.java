package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.AwardedDegreeDto;
import es.um.asio.service.mapper.decorator.AwardedDegreeMapperDecorator;

@Mapper
@DecoratedWith(AwardedDegreeMapperDecorator.class)
public interface AwardedDegreeMapper extends BaseMapper<AwardedDegreeDto> {
	
}