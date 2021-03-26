package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.EmploymentContractDto;
import es.um.asio.service.mapper.decorator.EmploymentContractMapperDecorator;

@Mapper
@DecoratedWith(EmploymentContractMapperDecorator.class)
public interface EmploymentContractMapper extends BaseMapper<EmploymentContractDto> {

}
