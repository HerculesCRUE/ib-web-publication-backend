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
			list1.add(delegate.convertSparqlQueryToDto(sparqlQuery));
		}

		return list1;
	}

	@Override
	public PageImplHelper<SparqlQueryDto> convertSparqlQueryPageToDto(Page<SparqlQuery> page) {
		if (page == null) {
			return null;
		}

		final List<SparqlQueryDto> list = this.delegate.convertSparqlQueryListToDto(page.getContent());

		return new PageImplHelper<>(list, page.getPageable(), page.getTotalElements());

	}
}
