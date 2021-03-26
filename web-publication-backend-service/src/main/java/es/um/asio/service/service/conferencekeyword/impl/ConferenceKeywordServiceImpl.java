package es.um.asio.service.service.conferencekeyword.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.filter.conferencekeyword.ConferenceKeywordFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.model.PageableQuery;
import es.um.asio.service.service.conference.impl.ConferenceServiceImpl;
import es.um.asio.service.service.conferencekeyword.ConferenceKeywordService;
import es.um.asio.service.service.impl.FusekiService;
import es.um.asio.service.service.sparql.SparqlExecQuery;

@Service
public class ConferenceKeywordServiceImpl extends FusekiService<ConferenceKeywordFilter> implements ConferenceKeywordService {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(ConferenceServiceImpl.class);

	@Autowired
	private SparqlExecQuery serviceSPARQL;
	
	@Override
	public Page<FusekiResponse> findPaginated(ConferenceKeywordFilter filter, Pageable pageable) {
		logger.info("Searching Conference keywords with filter: {} page: {}", filter, pageable);

		PageableQuery pageableQuery = new PageableQuery(this.retrieveEntity(), filtersChunk(filter), pageable);

		return serviceSPARQL.run(pageableQuery);
	}

	@Override
	public String filtersChunk(ConferenceKeywordFilter filter) {
		StringBuilder strBuilder = new StringBuilder();
		
		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getKeyword())) {
				strBuilder.append("FILTER (LANG(?keyword) = \"");
				strBuilder.append(filter.getLanguage().substring(1));
				strBuilder.append("\") . ");
				strBuilder.append("FILTER ( regex(?keyword, \"");
				strBuilder.append(filter.getKeyword());
				strBuilder.append("\", \"i\")) . ");
			}
		}
		
		return strBuilder.toString();
	}

	@Override
	public Entity retrieveEntity() {
		return new Entity("Conference-Keyword", "keyword");
	}
}
