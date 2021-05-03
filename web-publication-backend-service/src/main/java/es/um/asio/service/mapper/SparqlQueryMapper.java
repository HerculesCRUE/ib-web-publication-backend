package es.um.asio.service.mapper;

import java.util.List;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import es.um.asio.service.domain.SparqlQuery;
import es.um.asio.service.dto.SparqlQueryDto;
import es.um.asio.service.mapper.decorator.SparqlQueryMapperDecorator;
import es.um.asio.service.util.PageImplHelper;

@Mapper
@DecoratedWith(SparqlQueryMapperDecorator.class)
public interface SparqlQueryMapper {

	List<SparqlQueryDto> convertSparqlQueryListToDto(List<SparqlQuery> list);

	/**
	 * Convert sparqlQuery iterable from dto.
	 *
	 * @param list the list
	 * @return the iterable
	 */
	Iterable<SparqlQuery> convertSparqlQueryIterableFromDto(Iterable<SparqlQueryDto> list);

	/**
	 * Convert sparqlQuery page to dto.
	 *
	 * @param page the page
	 * @return the page impl helper
	 */
	PageImplHelper<SparqlQueryDto> convertSparqlQueryPageToDto(Page<SparqlQuery> page);

	/**
	 * Convert sparqlQuery to dto.
	 *
	 * @param MySparqlQuery the sparqlQuery
	 * @return the sparqlQuery dto
	 */
	SparqlQueryDto convertSparqlQueryToDto(SparqlQuery sparqlQuery);

	/**
	 * Convert sparqlQuery from dto.
	 *
	 * @param SparqlQueryMap the SparqlQuery map
	 * @return the sparqlQuery
	 */
	SparqlQuery convertSparqlQueryFromDto(SparqlQueryDto sparqlQueryDto);

	SparqlQuery updateSparqlQueryFromDto(SparqlQueryDto sparqlQueryMap, @MappingTarget SparqlQuery sparqlQuery);
}
