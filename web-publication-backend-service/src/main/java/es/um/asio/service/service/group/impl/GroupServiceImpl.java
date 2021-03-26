package es.um.asio.service.service.group.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.filter.gorup.GroupFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.model.PageableQuery;
import es.um.asio.service.service.article.impl.ArticleServiceImpl;
import es.um.asio.service.service.group.GroupService;
import es.um.asio.service.service.impl.FusekiService;
import es.um.asio.service.service.sparql.SparqlExecQuery;

@Service
public class GroupServiceImpl extends FusekiService<GroupFilter> implements GroupService {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

	@Autowired
	private SparqlExecQuery serviceSPARQL;
	
	@Override
	public Page<FusekiResponse> findPaginated(GroupFilter filter, Pageable pageable) {
		logger.info("Searching article keywords with filter: {} page: {}", filter, pageable);

		PageableQuery pageableQuery = new PageableQuery(this.retrieveEntity(), filtersChunk(filter), pageable);

		return serviceSPARQL.run(pageableQuery);
	}

	@Override
	public String filtersChunk(GroupFilter filter) {
		StringBuilder strBuilder = new StringBuilder();
		
		if (StringUtils.isNotBlank(filter.getId())) {
			strBuilder.append("FILTER (?id = \"");
			strBuilder.append(filter.getId());
			strBuilder.append("\"");
			strBuilder.append(filter.getLanguage());
			strBuilder.append(") . ");
		}
		
		return strBuilder.toString();
	}

	@Override
	public Entity retrieveEntity() {
		return new Entity("Group", "id", "date", "dedication", "dedicationPercentage", "description", "homepage");
	}
}
