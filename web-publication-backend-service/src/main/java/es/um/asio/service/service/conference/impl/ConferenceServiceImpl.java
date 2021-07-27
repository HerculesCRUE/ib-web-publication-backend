package es.um.asio.service.service.conference.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.filter.conference.ConferenceFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.model.PageableQuery;
import es.um.asio.service.service.conference.ConferenceService;
import es.um.asio.service.service.impl.FusekiService;
import es.um.asio.service.service.sparql.SparqlExecQuery;

@Service
public class ConferenceServiceImpl extends FusekiService<ConferenceFilter> implements ConferenceService {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(ConferenceServiceImpl.class);

	@Autowired
	private SparqlExecQuery serviceSPARQL;

	@Override
	public Page<FusekiResponse> findPaginated(ConferenceFilter filter, Pageable pageable) {
		logger.info("Searching congress with filter: {} page: {}", filter, pageable);

		PageableQuery pageableQuery = new PageableQuery(this.retrieveEntity(), filtersChunk(filter), pageable);

		return serviceSPARQL.run(pageableQuery);
	}

	@Override
	public String filtersChunk(ConferenceFilter filter) {
		StringBuilder strBuilder = new StringBuilder();

		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getAbbreviation())) {
				strBuilder.append("FILTER (?abbreviation = \"");
				strBuilder.append(filter.getAbbreviation());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getContactInformation())) {
				strBuilder.append("FILTER (?contactInformation = \"");
				strBuilder.append(filter.getContactInformation());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getDate())) {
				strBuilder.append("FILTER (?date = \"");
				strBuilder.append(filter.getDate());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getDescription())) {
				strBuilder.append("FILTER (LANG(?description) = \"");
				strBuilder.append(filter.getLanguage().substring(1));
				strBuilder.append("\") . ");
				strBuilder.append("FILTER ( regex(?description, \"");
				strBuilder.append(filter.getDescription());
				strBuilder.append("\", \"i\")) . ");
			}

			if (StringUtils.isNotBlank(filter.getId())) {
				strBuilder.append("FILTER (regex(?id, \"^");
				strBuilder.append(filter.getId());
				strBuilder.append("$\")) . ");
			}

			if (StringUtils.isNotBlank(filter.getLocality())) {
				strBuilder.append("FILTER (?locality = \"");
				strBuilder.append(filter.getLocality());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getLocatedIn())) {
				strBuilder.append("FILTER (?locatedIn = \"");
				strBuilder.append(filter.getLocatedIn());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getPresents())) {
				strBuilder.append("FILTER (?presents = \"");
				strBuilder.append(filter.getPresents());
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
		return new Entity("Conference", "abbreviation", "contactInformation", "date", "description", "id", "locality",
				"locatedIn", "presents", "title");
	}

}
