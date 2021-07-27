package es.um.asio.service.service.fundingsource.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.filter.fundingsource.FundingSourceFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.model.PageableQuery;
import es.um.asio.service.service.fundingsource.FundingSourceService;
import es.um.asio.service.service.impl.FusekiService;
import es.um.asio.service.service.sparql.SparqlExecQuery;

@Service
public class FundingSourceServiceImpl extends FusekiService<FundingSourceFilter> implements FundingSourceService {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(FundingSourceServiceImpl.class);

	@Autowired
	private SparqlExecQuery serviceSPARQL;

	@Override
	public Page<FusekiResponse> findPaginated(FundingSourceFilter filter, Pageable pageable) {
		logger.info("Searching funding sources with filter: {} page: {}", filter, pageable);

		PageableQuery pageableQuery = new PageableQuery(this.retrieveEntity(), filtersChunk(filter), pageable);

		return serviceSPARQL.run(pageableQuery);
	}

	@Override
	public String filtersChunk(FundingSourceFilter filter) {
		StringBuilder strBuilder = new StringBuilder();

		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getFunds())) {
				strBuilder.append("FILTER (?funds = \"");
				strBuilder.append(filter.getFunds());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getId())) {
				strBuilder.append("FILTER (regex(?id, \"^");
				strBuilder.append(filter.getId());
				strBuilder.append("$\")) . ");
			}
		}

		return strBuilder.toString();
	}

	@Override
	public Entity retrieveEntity() {
		return new Entity("FundingSource", "funds", "id");
	}

}
