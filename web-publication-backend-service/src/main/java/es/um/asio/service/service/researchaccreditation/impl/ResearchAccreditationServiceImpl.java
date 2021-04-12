package es.um.asio.service.service.researchaccreditation.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.filter.researchaccreditation.ResearchAccreditationFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.model.PageableQuery;
import es.um.asio.service.service.impl.FusekiService;
import es.um.asio.service.service.researchaccreditation.ResearchAccreditationService;
import es.um.asio.service.service.sparql.SparqlExecQuery;

@Service
public class ResearchAccreditationServiceImpl extends FusekiService<ResearchAccreditationFilter> implements ResearchAccreditationService {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(ResearchAccreditationServiceImpl.class);

	@Autowired
	private SparqlExecQuery serviceSPARQL;
	
	@Override
	public Page<FusekiResponse> findPaginated(ResearchAccreditationFilter filter, Pageable pageable) {
		logger.info("Searching ResearchAccreditations with filter: {} page: {}", filter, pageable);

		PageableQuery pageableQuery = new PageableQuery(this.retrieveEntity(), filtersChunk(filter), pageable);

		return serviceSPARQL.run(pageableQuery);
	}

	@Override
	public String filtersChunk(ResearchAccreditationFilter filter) {
		StringBuilder strBuilder = new StringBuilder();
		
		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getDateIssued())) {
				strBuilder.append("FILTER (?dateIssued = \"");
				strBuilder.append(filter.getDateIssued());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}
			
			if (StringUtils.isNotBlank(filter.getDescription())) {
				strBuilder.append("FILTER (?description = \"");
				strBuilder.append(filter.getDescription());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}
			
			if (StringUtils.isNotBlank(filter.getHasSubjectArea())) {
				strBuilder.append("FILTER (?hasSubjectArea = \"");
				strBuilder.append(filter.getHasSubjectArea());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}
			
			if (StringUtils.isNotBlank(filter.getId())) {
				strBuilder.append("FILTER (?id = \"");
				strBuilder.append(filter.getId());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getTitle())) {
				strBuilder.append("FILTER (LANG(?title) = \"");
				strBuilder.append(filter.getLanguage().substring(1));
				strBuilder.append("\") . ");
				strBuilder.append("FILTER ( regex(?title, \"");
				strBuilder.append(filter.getTitle());
				strBuilder.append("\", \"i\")) . ");
			}
		}
		
		return strBuilder.toString();
	}

	@Override
	public Entity retrieveEntity() {
		return new Entity("ResearchAccreditation", "dateIssued", "description", "hasSubjectArea", "id", "title");
	}
}
