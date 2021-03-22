package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.OrganizationDetailDto;
import es.um.asio.service.mapper.decorator.OrganizationDetailMapperDecorator;

@Mapper
@DecoratedWith(OrganizationDetailMapperDecorator.class)
public interface OrganizationDetailMapper extends BaseMapper<OrganizationDetailDto> {

}
