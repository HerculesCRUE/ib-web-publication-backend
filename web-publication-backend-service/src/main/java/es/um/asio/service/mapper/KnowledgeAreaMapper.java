package es.um.asio.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import es.um.asio.service.dto.KnowledgeAreaDto;
import es.um.asio.service.mapper.decorator.KnowledgeAreaMapperDecorator;

@Mapper
@DecoratedWith(KnowledgeAreaMapperDecorator.class)
public interface KnowledgeAreaMapper extends BaseMapper<KnowledgeAreaDto> {

}
