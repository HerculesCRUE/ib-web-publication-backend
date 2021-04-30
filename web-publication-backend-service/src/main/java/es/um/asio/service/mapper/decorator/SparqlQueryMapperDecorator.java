package es.um.asio.service.mapper.decorator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.domain.SparqlQuery;
import es.um.asio.service.dto.SparqlQueryDto;
import es.um.asio.service.mapper.SparqlQueryMapper;
import es.um.asio.service.util.PageImplHelper;

public abstract class SparqlQueryMapperDecorator implements SparqlQueryMapper {

	/** The delegate. */
	@Autowired
	@Qualifier("delegate")
	private SparqlQueryMapper delegate;

	@Override
	public List<SparqlQueryDto> convertSparqlQueryListToDto(List<SparqlQuery> list) {
		if (list == null) {
			return null;
		}

		List<SparqlQueryDto> list1 = new ArrayList<>(list.size());
		for (SparqlQuery sparqlQuery : list) {
			list1.add(convertSparqlQueryToDto(sparqlQuery));
		}

		return list1;
	}

	@Override
	public SparqlQuery convertSparqlQueryFromDto(SparqlQueryDto sparqlQueryDto) {
		if (sparqlQueryDto == null) {
			return null;
		}
		SparqlQuery query = this.delegate.convertSparqlQueryFromDto(sparqlQueryDto);

		query.setEntityId(sparqlQueryDto.getEntityId());

		return query;
	}

	@Override
	public SparqlQueryDto convertSparqlQueryToDto(SparqlQuery sparqlQuery) {
		if (sparqlQuery == null) {
			return null;
		}
		SparqlQueryDto query = this.delegate.convertSparqlQueryToDto(sparqlQuery);

		query.setEntityId(sparqlQuery.getEntityId());

		return query;
	}

	@Override
	public PageImplHelper<SparqlQueryDto> convertSparqlQueryPageToDto(Page<SparqlQuery> page) {
		if (page == null) {
			return null;
		}

		final List<SparqlQueryDto> list = this.convertSparqlQueryListToDto(page.getContent());

		return new PageImplHelper<>(list, page.getPageable(), page.getTotalElements());

	}

}
