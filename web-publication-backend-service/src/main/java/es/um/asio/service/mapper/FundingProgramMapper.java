package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.FundingProgramDto;
import es.um.asio.service.mapper.decorator.FundingProgramMapperDecorator;

@Mapper
@DecoratedWith(FundingProgramMapperDecorator.class)
public interface FundingProgramMapper extends BaseMapper<FundingProgramDto> {
	
}