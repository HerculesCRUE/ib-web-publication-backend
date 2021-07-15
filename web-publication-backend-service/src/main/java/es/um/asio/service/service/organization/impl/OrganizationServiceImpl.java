package es.um.asio.service.service.organization.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.abstractions.constants.Constants;
import es.um.asio.service.filter.organization.OrganizationFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.model.PageableQuery;
import es.um.asio.service.model.SimpleQuery;
import es.um.asio.service.service.impl.FusekiService;
import es.um.asio.service.service.organization.OrganizationService;
import es.um.asio.service.service.sparql.SparqlExecQuery;

@Service
public class OrganizationServiceImpl extends FusekiService<OrganizationFilter> implements OrganizationService {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class);

	@Autowired
	private SparqlExecQuery serviceSPARQL;

	@Override
	public Page<FusekiResponse> findPaginated(OrganizationFilter filter, Pageable pageable) {
		logger.info("Searching Organizations with filter: {} page: {}", filter, pageable);

		PageableQuery pageableQuery = new PageableQuery(this.retrieveEntity(filter), filtersChunk(filter), pageable);

		return serviceSPARQL.run(pageableQuery);
	}

	@Override
	public List<Object> find(String id, String type) {
		logger.info("Searching Organization with id: {} type: {}", id, type);

		SimpleQuery query = new SimpleQuery(this.retrieveEntity(type), filtersChunk(id));

		return serviceSPARQL.run(query);
	}

	@Override
	public String filtersChunk(OrganizationFilter filter) {
		StringBuilder strBuilder = new StringBuilder();

		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getAbbreviation())) {
				strBuilder.append("FILTER (?abbreviation = \"");
				strBuilder.append(filter.getAbbreviation());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}
//
//			if (StringUtils.isNotBlank(filter.getDescription())) {
//				strBuilder.append("FILTER (LANG(?description) = \"");
//				strBuilder.append(filter.getLanguage().substring(1));
//				strBuilder.append("\") . ");
//				strBuilder.append("FILTER ( regex(?description, \"");
//				strBuilder.append(filter.getDescription());
//				strBuilder.append("\", \"i\")) . ");
//			}

			if (StringUtils.isNotBlank(filter.getEndDate())) {
				strBuilder.append("FILTER (?dateEnd = \"");
				strBuilder.append(filter.getEndDate());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getHomepage())) {
				strBuilder.append("FILTER (?homepage = \"");
				strBuilder.append(filter.getHomepage());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getId())) {
				strBuilder.append("FILTER (regex(?id, \"^");
				strBuilder.append(filter.getId());
				strBuilder.append("$\")) . ");
			}

			if (StringUtils.isNotBlank(filter.getIsStartup())) {
				strBuilder.append("FILTER (?isStartup = \"");
				strBuilder.append(filter.getIsStartup());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getPublicCompany())) {
				strBuilder.append("FILTER (?publicCompany = \"");
				strBuilder.append(filter.getPublicCompany());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getStartDate())) {
				strBuilder.append("FILTER (?dateStart = \"");
				strBuilder.append(filter.getStartDate());
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
	public String filtersChunk(String id) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("FILTER (?id = \"");
		strBuilder.append(id);
		strBuilder.append("\"@");
		strBuilder.append(Constants.SPANISH_LANGUAGE_SHORT);
		strBuilder.append(") . ");

		return strBuilder.toString();
	}

	@Override
	public Entity retrieveEntity(OrganizationFilter filter) {
		List<String> types = StringUtils.isNotBlank(filter.getTypes()) ? Arrays.asList(filter.getTypes().split(","))
				: Arrays.asList("Organization", "University");

		return new Entity("Organization", types, "abbreviation", "id", "title", "description", "nowhere:type");
	}

	@Override
	public Entity retrieveEntity(String type) {
		List<String> types = new ArrayList<String>();
		String[] splitType = type.split("/");
		types.add(splitType[splitType.length - 1]);

		Entity entity;

		List<String> optionalFields = new ArrayList<>();

		// TODO Pending update of ETL: University should have at least the same fields
		// as Organization
		if (type.equals("University")) {
			entity = new Entity("Organization", types, "abbreviation", "id", "title", "description", "nowhere:type");
		} else {
			entity = new Entity("Organization", types, "abbreviation", "description", "dateEnd", "id", "keyword",
					"dateStart", "title", "nowhere:type");

			optionalFields.add("dateEnd");
			optionalFields.add("keyword");
			optionalFields.add("dateStart");
		}

		optionalFields.add("publicCompany");
		optionalFields.add("isStartup");
		optionalFields.add("homepage");
		entity.setOptionalFields(optionalFields);

		return entity;
	}

	@Override
	public Entity retrieveEntity() {
		throw new NotImplementedException("retrieveEntity: Not implemented method");
	}

}
