package es.um.asio.service.service.konwledgearea.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.filter.knowledgearea.KnowledgeAreaFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.model.PageableQuery;
import es.um.asio.service.model.SimpleQuery;
import es.um.asio.service.service.impl.FusekiService;
import es.um.asio.service.service.konwledgearea.KnowledgeAreaService;
import es.um.asio.service.service.sparql.SparqlExecQuery;

@Service
public class KnowledgeAreaServiceImpl extends FusekiService<KnowledgeAreaFilter> implements KnowledgeAreaService {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(KnowledgeAreaServiceImpl.class);

	@Autowired
	private SparqlExecQuery serviceSPARQL;
	
	@Override
	public Page<FusekiResponse> findPaginated(KnowledgeAreaFilter filter, Pageable pageable) {
		logger.info("Searching KnowledgeAreas with filter: {} page: {}", filter, pageable);

		PageableQuery pageableQuery = new PageableQuery(this.retrieveEntity(), filtersChunk(filter), pageable);

		return serviceSPARQL.run(pageableQuery);
	}

	@Override
	public List<Object> findAll() {
		logger.info("Get all KnowledgeAreas");
		
		SimpleQuery query = new SimpleQuery(this.retrieveEntity(), "");
		
		return serviceSPARQL.run(query);
	}

	@Override
	public String filtersChunk(KnowledgeAreaFilter filter) {
		StringBuilder strBuilder = new StringBuilder();
		
		if (filter != null) {

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
		return new Entity("Knowledge-Area", "id", "title");
	}

}
