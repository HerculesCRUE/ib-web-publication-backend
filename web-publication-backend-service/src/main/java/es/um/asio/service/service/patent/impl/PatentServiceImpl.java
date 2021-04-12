package es.um.asio.service.service.patent.impl;

import java.util.ArrayList;
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
import es.um.asio.service.filter.patent.PatentFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.model.PageableQuery;
import es.um.asio.service.model.SimpleQuery;
import es.um.asio.service.model.Subentity;
import es.um.asio.service.service.patent.PatentService;
import es.um.asio.service.service.sparql.SparqlExecQuery;

@Service
public class PatentServiceImpl implements PatentService {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(PatentServiceImpl.class);

	@Autowired
	private SparqlExecQuery serviceSPARQL;

	@Override
	public Page<FusekiResponse> findPaginated(PatentFilter filter, Pageable pageable) {
		logger.info("Searching patents with filter: {} page: {}", filter, pageable);

		PageableQuery pageableQuery = new PageableQuery(this.retrieveEntity(filter), filtersChunk(filter), pageable);

		return serviceSPARQL.run(pageableQuery);
	}

	@Override
	public List<Object> find(String id) {
		logger.info("Searching patent with id: {} type: {}", id);

		SimpleQuery query = new SimpleQuery(this.retrieveDetailEntity(), filtersChunk(id));

		return serviceSPARQL.run(query);
	}

	public String filtersChunk(PatentFilter filter) {
		StringBuilder strBuilder = new StringBuilder();

		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getDateIssued())) {
				strBuilder.append("FILTER (?dateIssued = \"");
				strBuilder.append(filter.getDateIssued());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getDoi())) {
				strBuilder.append("FILTER (?doi = \"");
				strBuilder.append(filter.getDoi());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getEndDate())) {
				strBuilder.append("FILTER (?endDate = \"");
				strBuilder.append(filter.getEndDate());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getEndPage())) {
				strBuilder.append("FILTER (?pageEnd = \"");
				strBuilder.append(filter.getEndPage());
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

			if (StringUtils.isNotBlank(filter.getKeyword())) {
				strBuilder.append("FILTER (?keyword = \"");
				strBuilder.append(filter.getKeyword());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getMode())) {
				strBuilder.append("FILTER (?mode = \"");
				strBuilder.append(filter.getMode());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getStartDate())) {
				strBuilder.append("FILTER (?StartDate = \"");
				strBuilder.append(filter.getStartDate());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getStartPage())) {
				strBuilder.append("FILTER (?startPage = \"");
				strBuilder.append(filter.getStartPage());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getSummary())) {
				strBuilder.append("FILTER (LANG(?summary) = \"");
				strBuilder.append(filter.getLanguage().substring(1));
				strBuilder.append("\") . ");
				strBuilder.append("FILTER ( regex(?summary, \"");
				strBuilder.append(filter.getSummary());
				strBuilder.append("\", \"i\")) . ");
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
	public Entity retrieveEntity(PatentFilter filter) {
		Entity entity = new Entity("Patent", "dateIssued", "doi", "endDate", "pageEnd", "id", "keyword", "mode",
				"pageStart", "startDate", "title");

		// Add data to subentity atributes and filters
		if (filter.getOrganizationId() != null && !filter.getOrganizationId().isEmpty()) {
			List<Subentity> subentities = new ArrayList<Subentity>();
			// Extra fields
			String fieldName = "correspondingOrganization";
//			List<String> fields = new ArrayList<String>();
//			fields.add("id");
//			entity.getFields().add(fieldName+"Id");
			Subentity subentity = new Subentity();
			subentity.setFieldName(fieldName);
			Map<String, String> filters = new HashMap<>();
			filters.put("id", filter.getOrganizationId());
			subentity.setFilters(filters);
			subentities.add(subentity);
			entity.setSubentities(subentities);
		}

		// Add data to subentity atributes and filters
		if (filter.getAuthorId() != null && !filter.getAuthorId().isEmpty()) {
			List<Subentity> subentities = new ArrayList<Subentity>();
			// Extra fields
			String fieldName = "correspondingAuthor";
//			List<String> fields = new ArrayList<String>();
//			fields.add("id");
//			entity.getFields().add(fieldName+"Id");
			Subentity subentity = new Subentity();
			subentity.setFieldName(fieldName);
			Map<String, String> filters = new HashMap<>();
			filters.put("id", filter.getAuthorId());
			subentity.setFilters(filters);
			subentities.add(subentity);
			entity.setSubentities(subentities);
		}

		return entity;
	}

	/**
	 * Retrieve document detail entity.
	 *
	 * @param type the type
	 * @return the entity
	 */
	@Override
	public Entity retrieveDetailEntity() {
		return new Entity("Patent", "dateIssued", "doi", "endDate", "pageEnd", "id", "keyword", "mode", "pageStart",
				"startDate", "title");

	}

	@Override
	public List<Object> getbyOrganization() {

		SimpleQuery query = new SimpleQuery(this.retrieveGraphicEntity(), "");

		return serviceSPARQL.runCount(query);
	}

	private Entity retrieveGraphicEntity() {
		Entity entity = new Entity("Patent", "ownerOrganization");
//		List<Subentity> subentities = new ArrayList<Subentity>();
//		// Extra fields
//		String fieldName = "ownerOrganization";
//		Subentity subentity = new Subentity();
//		subentity.setFieldName(fieldName);
//		subentities.add(subentity);
//		List<String> fields = new ArrayList<String>();
//		fields.add("id");
//		subentity.setFields(fields);
//		entity.setSubentities(subentities);

		List<String> groups = new ArrayList<>();
		groups.add("ownerOrganization");
		entity.setGroup(groups);

		return entity;
	}

}
