package es.um.asio.service.service.internationalproject.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.filter.internationalproject.InternationalProjectFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.model.PageableQuery;
import es.um.asio.service.service.impl.FusekiService;
import es.um.asio.service.service.internationalproject.InternationalProjectService;
import es.um.asio.service.service.sparql.SparqlExecQuery;

@Service
public class InternationalProjectServiceImpl extends FusekiService<InternationalProjectFilter>
		implements InternationalProjectService {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(InternationalProjectServiceImpl.class);

	@Autowired
	private SparqlExecQuery serviceSPARQL;

	@Override
	public Page<FusekiResponse> findPaginated(InternationalProjectFilter filter, Pageable pageable) {
		logger.info("Searching international projects with filter: {} page: {}", filter, pageable);

		PageableQuery pageableQuery = new PageableQuery(this.retrieveEntity(), filtersChunk(filter), pageable);

		return serviceSPARQL.run(pageableQuery);
	}

	@Override
	public String filtersChunk(InternationalProjectFilter filter) {
		StringBuilder strBuilder = new StringBuilder();

		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getEndDate())) {
				strBuilder.append("FILTER (?endDate = \"");
				strBuilder.append(filter.getEndDate());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getId())) {
				strBuilder.append("FILTER (regex(?id, \"^");
				strBuilder.append(filter.getId());
				strBuilder.append("$\")) . ");
			}

			if (StringUtils.isNotBlank(filter.getName())) {
				strBuilder.append("FILTER (LANG(?name) = \"");
				strBuilder.append(filter.getLanguage().substring(1));
				strBuilder.append("\") . ");
				strBuilder.append("FILTER ( regex(?name, \"");
				strBuilder.append(filter.getName());
				strBuilder.append("\", \"i\")) . ");
			}

			if (StringUtils.isNotBlank(filter.getObjective())) {
				strBuilder.append("FILTER (LANG(?objective) = \"");
				strBuilder.append(filter.getLanguage().substring(1));
				strBuilder.append("\") . ");
				strBuilder.append("FILTER ( regex(?objective, \"");
				strBuilder.append(filter.getObjective());
				strBuilder.append("\", \"i\")) . ");
			}

			if (StringUtils.isNotBlank(filter.getStartDate())) {
				strBuilder.append("FILTER (?startDate = \"");
				strBuilder.append(filter.getStartDate());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}
		}

		return strBuilder.toString();
	}

	@Override
	public Entity retrieveEntity() {
		return new Entity("InternationalProject", "endDate", "id", "name", "objective", "startDate");
	}

}
