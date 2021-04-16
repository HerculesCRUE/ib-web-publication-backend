package es.um.asio.service.service.scientificpublication.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.abstractions.constants.Constants;
import es.um.asio.service.filter.scientificpublication.ScientificPublicationFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.model.PageableQuery;
import es.um.asio.service.model.SimpleQuery;
import es.um.asio.service.model.Subentity;
import es.um.asio.service.service.impl.FusekiService;
import es.um.asio.service.service.scientificpublication.ScientificPublicationService;
import es.um.asio.service.service.sparql.SparqlExecQuery;

@Service
public class ScientificPublicationServiceImpl extends FusekiService<ScientificPublicationFilter>
		implements ScientificPublicationService {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(ScientificPublicationServiceImpl.class);

	@Autowired
	private SparqlExecQuery serviceSPARQL;

	@Override
	public Page<FusekiResponse> findPaginated(ScientificPublicationFilter filter, Pageable pageable) {
		logger.info("Searching ScientificPublications with filter: {} page: {}", filter, pageable);

		PageableQuery pageableQuery = new PageableQuery(this.retrieveEntity(), filtersChunk(filter), pageable);

		return serviceSPARQL.run(pageableQuery);
	}

	@Override
	public List<Object> find(String id) {
		logger.info("Searching ScientificPublication with id: {} type: {}", id);

		SimpleQuery query = new SimpleQuery(this.retrieveEntity(), filtersChunk(id));

		return serviceSPARQL.run(query);
	}

	@Override
	public String filtersChunk(ScientificPublicationFilter filter) {
		StringBuilder strBuilder = new StringBuilder();

		if (filter != null) {
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
		return new Entity("Scientific-Publication", "id", "title", "abbreviation", "date", "description", "homepage",
				"isStartup", "keyword", "publicCompany");
	}

	private String filtersChunk(String id) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("FILTER (?id = \"");
		strBuilder.append(id);
		strBuilder.append("\"@");
		strBuilder.append(Constants.SPANISH_LANGUAGE_SHORT);
		strBuilder.append(") . ");

		return strBuilder.toString();
	}

	@Override
	public List<Object> publicationByPerson(String id) {
		SimpleQuery query = new SimpleQuery(this.retrievePublicationByPersonEntity(id), "");

		return serviceSPARQL.runCount(query);
	}

	public Entity retrievePublicationByPersonEntity(String id) {
		List<String> types = Arrays.asList("Article", "Book");
		Entity entity = new Entity("Publication", types, "nowhere:type");

		// Add data to subentity atributes and filters
		if (id != null && !id.isEmpty()) {

			List<Subentity> subentities = new ArrayList<Subentity>();
			// Extra fields
			String fieldName = "correspondingAuthor";
			Subentity subentity = new Subentity();
			subentity.setFieldName(fieldName);
			Map<String, String> filters = new HashMap<>();
			String idregex = "^" + id + "$";
			filters.put("id", idregex);
			subentity.setFilters(filters);
			// Add All
			subentities.add(subentity);
			entity.setSubentities(subentities);
		}

		List<String> groups = new ArrayList<>();
		groups.add("type");
		entity.setGroup(groups);

		return entity;
	}
}
