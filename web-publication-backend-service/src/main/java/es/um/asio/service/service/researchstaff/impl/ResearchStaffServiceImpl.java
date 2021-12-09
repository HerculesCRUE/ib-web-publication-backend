package es.um.asio.service.service.researchstaff.impl;

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

import es.um.asio.service.filter.researchstaff.ResearchStaffFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.model.PageableQuery;
import es.um.asio.service.model.Subentity;
import es.um.asio.service.service.impl.FusekiService;
import es.um.asio.service.service.researchstaff.ResearchStaffService;
import es.um.asio.service.service.sparql.SparqlExecQuery;

@Service
public class ResearchStaffServiceImpl extends FusekiService<ResearchStaffFilter> implements ResearchStaffService {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(ResearchStaffServiceImpl.class);

	@Autowired
	private SparqlExecQuery serviceSPARQL;

	@Override
	public Page<FusekiResponse> findPaginated(ResearchStaffFilter filter, Pageable pageable) {
		logger.info("Searching research staff with filter: {} page: {}", filter, pageable);

		PageableQuery pageableQuery = new PageableQuery(this.retrieveEntity(filter), filtersChunk(filter), pageable);

		return serviceSPARQL.runDistinct(pageableQuery);
	}

	@Override
	public String filtersChunk(ResearchStaffFilter filter) {
		StringBuilder strBuilder = new StringBuilder();

		if (StringUtils.isNotBlank(filter.getName())) {
			strBuilder.append("FILTER (LANG(?name) = \"");
			strBuilder.append(filter.getLanguage().substring(1));
			strBuilder.append("\") . ");
			strBuilder.append("FILTER ( regex(?name, \"");
			strBuilder.append(filter.getName());
			strBuilder.append("\", \"i\")) . ");
		}

		if (filter.getKnowledgeAreas() != null && filter.getKnowledgeAreas().size() > 0) {
			strBuilder.append("FILTER (LANG(?hasKnowledgeAreaid) = \"");
			strBuilder.append(filter.getLanguage().substring(1));
			strBuilder.append("\") . ");
			strBuilder.append("FILTER ( ");

			int i = 0;
			for (String data : filter.getKnowledgeAreas()) {
				if (i == 0) {
					strBuilder.append(" regex (?hasKnowledgeAreaid ,  \"^");
					strBuilder.append(data);
				} else {
					strBuilder.append("$\") || regex(?hasKnowledgeAreaid ,  \"^");
					strBuilder.append(data);
				}

				i++;
			}

			strBuilder.append("$\")) . ");
		}

		if (StringUtils.isNotBlank(filter.getTitle())) {
			strBuilder.append("FILTER (LANG(?title) = \"");
			strBuilder.append(filter.getLanguage().substring(1));
			strBuilder.append("\") . ");
			strBuilder.append("FILTER ( regex(?title, \"");
			strBuilder.append(filter.getTitle());
			strBuilder.append("\", \"i\")) . ");
		}

		return strBuilder.toString();
	}

	@Override
	public Entity retrieveEntity(ResearchStaffFilter filter) {
		Entity entity = new Entity("Researcher-role", "nowhere:id", "nowhere:gender", "nowhere:name",
				"nowhere:nickname", "nowhere:personalMaibox", "nowhere:researchLine", "nowhere:hasKnowledgeAreatitle","freetext:(?inheresIn AS ?uri)");

		List<Subentity> subentities = new ArrayList<Subentity>();
		Subentity subentity = new Subentity();

		String fieldName = "inheresIn";

		subentity.setIgnorePrefix(true);
		subentity.setFieldName(fieldName);
		subentity.setFields(Arrays.asList("birthDate", "description", "firstName", "gender", "hasContactInfo",
				"homepage", "id", "image", "name", "nickname", "personalMaibox", "researchLine", "surname", "taxId"));

		Map<String, String> filters = new HashMap<>();
		subentity.setFilters(filters);
		subentities.add(subentity);

		if (filter.getOrganizationId() != null && !StringUtils.isBlank(filter.getOrganizationId())) {
			Subentity subentity2 = new Subentity();

			String fieldName2 = "relatedBy";

			subentity2.setIgnorePrefix(true);
			subentity2.setFieldName(fieldName2);

			// Extra fields
			String fieldName3 = "relates";
			Subentity subentity3 = new Subentity();
			subentity3.setIgnorePrefix(true);

			Map<String, String> filters2 = new HashMap<>();
			filters2.put("id", filter.getOrganizationId());
			subentity3.setFilters(filters2);

			subentity3.setFieldName(fieldName3);

			// Add All
			subentity2.setSubentities(new ArrayList<Subentity>());
			subentity2.getSubentities().add(subentity3);

			subentities.add(subentity2);

		} else if (filter.getKnowledgeAreas() != null && filter.getKnowledgeAreas().size() > 0) {
			Subentity subentity2 = new Subentity();

			String fieldName2 = "relatedBy";

			subentity2.setIgnorePrefix(true);
			subentity2.setFieldName(fieldName2);

			// Extra fields
			String fieldName3 = "relates";
			Subentity subentity3 = new Subentity();
			subentity3.setIgnorePrefix(true);

			subentity3.setFieldName(fieldName3);

			// Extra fields
			String fieldName4 = "hasKnowledgeArea";
			Subentity subentity4 = new Subentity();
			// subentity2.setIgnorePrefix(true);
			subentity4.setQueryFieldName(fieldName4);
			subentity4.setFieldName(fieldName4);
			subentity4.setFields(Arrays.asList("id", "title"));

			// Add All
			subentity3.setSubentities(new ArrayList<Subentity>());
			subentity3.getSubentities().add(subentity4);

			// Add All
			subentity2.setSubentities(new ArrayList<Subentity>());
			subentity2.getSubentities().add(subentity3);

			subentities.add(subentity2);
		}
		entity.setSubentities(subentities);
		return entity;
	}

	@Override
	public Entity retrieveEntity() {
		// TODO Auto-generated method stub
		return null;
	}
}
