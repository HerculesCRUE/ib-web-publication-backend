package es.um.asio.service.service.otherpublication.impl;

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

import es.um.asio.service.filter.otherpublication.OtherPublicationFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.model.PageableQuery;
import es.um.asio.service.model.SimpleQuery;
import es.um.asio.service.model.Subentity;
import es.um.asio.service.service.document.impl.DocumentServiceImpl;
import es.um.asio.service.service.impl.FusekiService;
import es.um.asio.service.service.otherpublication.OtherPublicationService;
import es.um.asio.service.service.sparql.SparqlExecQuery;

@Service
public class OtherPublicationServiceImpl extends FusekiService<OtherPublicationFilter>
		implements OtherPublicationService {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);

	@Autowired
	private SparqlExecQuery serviceSPARQL;

	@Override
	public Page<FusekiResponse> findPaginated(OtherPublicationFilter filter, Pageable pageable) {
		logger.info("Searching other publications with filter: {} page: {}", filter, pageable);

		PageableQuery pageableQuery = new PageableQuery(this.retrieveEntity(filter), filtersChunk(filter), pageable);

		return serviceSPARQL.run(pageableQuery);
	}

	@Override
	public List<Object> find(String id, String type) {
		logger.info("Searching document with id: {} type: {}", id, type);

		SimpleQuery query = new SimpleQuery(this.retrieveEntity(type), filtersChunk(id));

		return serviceSPARQL.run(query);
	}

	@Override
	public String filtersChunk(String id) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("FILTER (regex(?id, \"^");
		strBuilder.append(id);
		strBuilder.append("$\")) . ");

		return strBuilder.toString();
	}

	@Override
	public String filtersChunk(OtherPublicationFilter filter) {
		StringBuilder strBuilder = new StringBuilder();

		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getDate())) {
				strBuilder.append("FILTER (?date = \"");
				strBuilder.append(filter.getDate());
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

			if (StringUtils.isNotBlank(filter.getDateFrom())) {
				strBuilder.append("FILTER (?date >= \"");
				strBuilder.append(filter.getDateFrom());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getDateTo())) {
				strBuilder.append("FILTER (?date <= \"");
				strBuilder.append(filter.getDateTo());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}
		}

		return strBuilder.toString();
	}

	@Override
	public Entity retrieveEntity(OtherPublicationFilter filter) {
		List<String> types = StringUtils.isNotBlank(filter.getTypes()) ? Arrays.asList(filter.getTypes().split(","))
				: Arrays.asList("Dossier", "Other");
		
		List<String> typesReplace = new ArrayList<>();
		
		for (String type : types) {
			typesReplace.add(type.replace("Other", "Publication"));
		}

		Entity entity = new Entity("OtherPublication", typesReplace, "id", "title", "date", "nowhere:type");
		
		entity.setOptionalFields(Arrays.asList("description", "ocicnum"));
		
		if (StringUtils.isNotBlank(filter.getAuthorId())) {
			List<Subentity> subentities = new ArrayList<Subentity>();

			String fieldName = "correspondingAuthor";

			Map<String, String> filters = new HashMap<>();
			filters.put("id", filter.getAuthorId());

			Subentity subentity = new Subentity();
			subentity.setFieldName(fieldName);
			subentity.setFilters(filters);
			subentities.add(subentity);
			entity.setSubentities(subentities);
		}

		return entity;
	}

	@Override
	public Entity retrieveEntity(String type) {
		List<String> types = new ArrayList<String>();
		String[] splitType = type.split("/");
		types.add(splitType[splitType.length - 1]);	
		
		if(type.equals("Dossier")) {
			return new Entity("OtherPublication", types, "id", "title", "date", "description", "ocicnum", "nowhere:type");			
		} else if(type.equals("Publication")){
			return new Entity("OtherPublication", types, "id", "title", "date", "keyword", "summary", "doi", "PageStart", "PageEnd", "nowhere:type");
		}

		return null;
	}

	@Override
	public Entity retrieveEntity() {
		throw new NotImplementedException("retrieveEntity: Not implemented method");
	}

}
