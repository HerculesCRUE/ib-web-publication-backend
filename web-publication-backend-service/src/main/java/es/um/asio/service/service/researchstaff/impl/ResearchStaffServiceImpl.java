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
		
		PageableQuery pageableQuery = new PageableQuery(this.retrieveEntity(), filtersChunk(filter), pageable);

		return serviceSPARQL.run(pageableQuery);
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
	public Entity retrieveEntity() {
		Entity entity = new Entity("Researcher-role", "nowhere:id", "nowhere:gender", "nowhere:name", "nowhere:nickname", 
				"nowhere:personalMaibox", "nowhere:researchLine");
		
//		Map<String, Map<String, String>> join = new HashMap<>();
//		
//		join.put("Researcher-Role", new HashMap<>());
//		join.get("Researcher-Role").put("x", "inheresIn");
		
//		entity.setJoin(join);
		
		List<Subentity> subentities = new ArrayList<Subentity>();
		Subentity subentity = new Subentity();
		
		String fieldName = "inheresIn";
		
		subentity.setIgnorePrefix(true);
		subentity.setFieldName(fieldName);
		subentity.setFields(Arrays.asList("birthDate", "description", "firstName", "gender", "hasContactInfo", "homepage", "id", "image", "name", "nickname", 
				"personalMaibox", "researchLine", "surname", "taxId"));
		
		Map<String, String> filters = new HashMap<>();
		subentity.setFilters(filters);
		subentities.add(subentity);
		
		entity.setSubentities(subentities);
		
		return entity;
	}
}
