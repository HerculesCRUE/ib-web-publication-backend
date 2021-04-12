package es.um.asio.service.service.researcherrole.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.filter.researcherrole.ResearcherRoleFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.model.PageableQuery;
import es.um.asio.service.service.impl.FusekiService;
import es.um.asio.service.service.researcherrole.ResearcherRoleService;
import es.um.asio.service.service.sparql.SparqlExecQuery;

@Service
public class ResearcherRoleServiceImpl extends FusekiService<ResearcherRoleFilter> implements ResearcherRoleService {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(ResearcherRoleServiceImpl.class);

	@Autowired
	private SparqlExecQuery serviceSPARQL;
	
	@Override
	public Page<FusekiResponse> findPaginated(ResearcherRoleFilter filter, Pageable pageable) {
		logger.info("Searching ResearcherRoles with filter: {} page: {}", filter, pageable);

		PageableQuery pageableQuery = new PageableQuery(this.retrieveEntity(), filtersChunk(filter), pageable);

		return serviceSPARQL.run(pageableQuery);
	}

	@Override
	public String filtersChunk(ResearcherRoleFilter filter) {
		StringBuilder strBuilder = new StringBuilder();
		
		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getId())) {
				strBuilder.append("FILTER (?id = \"");
				strBuilder.append(filter.getId());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}
		}
		
		return strBuilder.toString();
	}

	@Override
	public Entity retrieveEntity() {
		return new Entity("ResearcherRole", "id", "date", "dedication", "dedicationPercentage", "description", "hasKnowledgeArea");
	}
}
