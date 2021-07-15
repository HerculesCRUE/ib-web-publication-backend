package es.um.asio.service.service.project.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.abstractions.constants.Constants;
import es.um.asio.service.filter.person.PersonFilter;
import es.um.asio.service.filter.project.ProjectFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.model.PageableQuery;
import es.um.asio.service.model.SimpleQuery;
import es.um.asio.service.model.Subentity;
import es.um.asio.service.service.impl.FusekiService;
import es.um.asio.service.service.project.ProjectService;
import es.um.asio.service.service.sparql.SparqlExecQuery;

@Service
public class ProjectServiceImpl extends FusekiService<ProjectFilter> implements ProjectService {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

	@Autowired
	private SparqlExecQuery serviceSPARQL;

	@Override
	public Page<FusekiResponse> findPaginated(ProjectFilter filter, Pageable pageable) {
		logger.info("Searching projects with filter: {} page: {}", filter, pageable);

		PageableQuery pageableQuery = new PageableQuery(this.retrieveEntity(filter), filtersChunk(filter), pageable);

		return serviceSPARQL.run(pageableQuery);

	}

	@Override
	public List<Object> find(String id) {
		logger.info("Searching project with id: {} type: {}", id);

		SimpleQuery query = new SimpleQuery(this.retrieveDetailEntity(), filtersChunk(id));

		return serviceSPARQL.run(query);
	}

	@Override
	public Page<FusekiResponse> getParticipants(String id, final PersonFilter filter, final Pageable pageable) {
		logger.info("Searching participants with project id: {} type: {}", id);

		PageableQuery query = new PageableQuery(this.retrieveParticipantsEntity(), filtersChunkParticipants(id, filter),
				pageable);

		return serviceSPARQL.run(query);
	}

	private String filtersChunkParticipants(String id, PersonFilter filter) {
		StringBuilder strBuilder = new StringBuilder();

		if (StringUtils.isNotBlank(id)) {
			strBuilder.append("FILTER (regex(?id, \"^");
			strBuilder.append(id);
			strBuilder.append("$\")) . ");
		}

		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getTitle())) {
				strBuilder.append("FILTER (LANG(?relatesTitle) = \"");
				strBuilder.append(filter.getLanguage().substring(1));
				strBuilder.append("\") . ");
				strBuilder.append("FILTER ( regex(?relatesTitle, \"");
				strBuilder.append(filter.getTitle());
				strBuilder.append("\", \"i\")) . ");
			}
		}

		return strBuilder.toString();
	}

	@Override
	public String filtersChunk(ProjectFilter filter) {
		StringBuilder strBuilder = new StringBuilder();
		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getAbbreviation())) {
				strBuilder.append("FILTER (?abbreviation = \"");
				strBuilder.append(filter.getAbbreviation());
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

			if (StringUtils.isNotBlank(filter.getEndDate())) {
				strBuilder.append("FILTER (?endDate <= \"");
				strBuilder.append(filter.getEndDate());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getForeseenJustificationDate())) {
				strBuilder.append("FILTER (?foreseenJustificationDate = \"");
				strBuilder.append(filter.getForeseenJustificationDate());
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

			if (StringUtils.isNotBlank(filter.getModality())) {
				strBuilder.append("FILTER (?modality = \"");
				strBuilder.append(filter.getModality());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getNeedsEthicalValidation())) {
				strBuilder.append("FILTER (?needsEthicalValidation = \"");
				strBuilder.append(filter.getNeedsEthicalValidation());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getStartDate())) {
				strBuilder.append("FILTER (?startDate >= \"");
				strBuilder.append(filter.getStartDate());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getStatus())) {
				strBuilder.append("FILTER (?status = \"");
				strBuilder.append(filter.getStatus());
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
	public Entity retrieveEntity(ProjectFilter filter) {
		Entity entity = new Entity("Project", "abbreviation", "endDate", "id", "projectClassification", "startDate",
				"modality", "title");

		// Add data to subentity atributes and filters
		if (filter.getAuthorId() != null && !filter.getAuthorId().isEmpty()) {
			// Get rol
			List<Subentity> subentities = new ArrayList<Subentity>();
			// Extra fields
			String fieldName = "relates";
//			List<String> fields = new ArrayList<String>();
//			fields.add("id");
//			entity.getFields().add(fieldName+"Id");
			Subentity subentity = new Subentity();
			subentity.setFieldName(fieldName);

			// Get person
			List<Subentity> subSubentities = new ArrayList<Subentity>();
			// Extra fields
			String fieldName2 = "RO_0000052";
			Subentity subSubentity = new Subentity();
			subSubentity.setFieldName(fieldName2);
			Map<String, String> filters2 = new HashMap<>();
			filters2.put("id", filter.getAuthorId());
			subSubentity.setFilters(filters2);
			subSubentities.add(subSubentity);

			// Add All
			subentity.setSubentities(subSubentities);
			subentities.add(subentity);
			entity.setSubentities(subentities);
		}

		return entity;
	}

	@Override
	public Entity retrieveDetailEntity() {
		return new Entity("Project", "abbreviation", "description", "endDate", "foreseenJustificationDate", "id",
				"keyword", "modality", "needsEthicalValidation", "projectClassification", "startDate", "title");
	}

	@Override
	public List<Object> getbyModality() {

		SimpleQuery query = new SimpleQuery(this.retrieveGraphicEntity(), "");

		return serviceSPARQL.runCount(query);
	}

	private Entity retrieveGraphicEntity() {
		Entity entity = new Entity("Project", "modality");
		List<String> groups = new ArrayList<>();
		groups.add("modality");
		entity.setGroup(groups);

		return entity;
	}

	@Override
	public Entity retrieveEntity() {

		throw new NotImplementedException("retrieveEntity: Not implemented method");
	}

	private Entity retrieveParticipantsEntity() {
		Entity entity = new Entity("Project", "id", "nowhere:inheresInBirthDate", "nowhere:inheresInDescription",
				"nowhere:inheresInFirstName", "nowhere:inheresInGender", "nowhere:inheresInImage",
				"nowhere:inheresInName", "nowhere:inheresInId", "nowhere:inheresInNickname",
				"nowhere:inheresInResearchLine", "nowhere:inheresInSurname", "nowhere:inheresInTaxId");

		List<Subentity> subentities = new ArrayList<Subentity>();
		// Extra fields
		String fieldName = "relates";

		Subentity subentity = new Subentity();
		subentity.setIgnorePrefix(true);
		subentity.setFieldName(fieldName);

		// Add All
		subentities.add(subentity);
		entity.setSubentities(subentities);

		// Extra fields
		String fieldName2 = "inheresIn";
		Subentity subentity2 = new Subentity();
		// subentity2.setIgnorePrefix(true);
		subentity2.setQueryFieldName(fieldName2);
		subentity2.setFieldName(fieldName2);
		subentity2.setFields(Arrays.asList("birthDate", "id", "description", "firstName", "gender", "image", "name",
				"nickname", "researchLine", "surname", "taxId"));

		// Add All
		subentity.setSubentities(new ArrayList<Subentity>());
		subentity.getSubentities().add(subentity2);
		entity.setSubentities(subentities);

		return entity;
	}

}
