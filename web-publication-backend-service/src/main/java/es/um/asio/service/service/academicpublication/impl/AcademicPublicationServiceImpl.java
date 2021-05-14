package es.um.asio.service.service.academicpublication.impl;

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
import es.um.asio.service.filter.academicpublication.AcademicPublicationFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.model.PageableQuery;
import es.um.asio.service.model.SimpleQuery;
import es.um.asio.service.model.Subentity;
import es.um.asio.service.service.academicpublication.AcademicPublicationService;
import es.um.asio.service.service.article.impl.ArticleServiceImpl;
import es.um.asio.service.service.impl.FusekiService;
import es.um.asio.service.service.sparql.SparqlExecQuery;

@Service
public class AcademicPublicationServiceImpl extends FusekiService<AcademicPublicationFilter>
		implements AcademicPublicationService {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

	@Autowired
	private SparqlExecQuery serviceSPARQL;

	@Override
	public Page<FusekiResponse> findPaginated(AcademicPublicationFilter filter, Pageable pageable) {
		logger.info("Searching AcademicPublications with filter: {} page: {}", filter, pageable);

		PageableQuery pageableQuery = new PageableQuery(this.retrieveEntity(filter), filtersChunk(filter), pageable);

		return serviceSPARQL.run(pageableQuery);
	}

	@Override
	public String filtersChunk(AcademicPublicationFilter filter) {
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
				strBuilder.append("\" && (regex(?title, \"");
				strBuilder.append(filter.getTitle());
				strBuilder.append("\", \"i\"))) . ");
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
	public Entity retrieveEntity(AcademicPublicationFilter filter) {
		List<String> types = StringUtils.isNotBlank(filter.getTypes()) ? Arrays.asList(filter.getTypes().split(","))
				: Arrays.asList("Doctoral-thesis");
//				Arrays.asList("Doctoral-thesis", "Master-Thesis");

		List<String> typesReplace = new ArrayList<>();

		for (String type : types) {
			typesReplace.add(type.replace("Thesis", "thesis"));
		}

		Entity entity = new Entity("AcademicPublication", typesReplace, "abbreviation", "date", "doi", "id", "title",
				"nowhere:type");

		// Add data to subentity atributes and filters
		if (filter.getDirectedBy() != null && !filter.getDirectedBy().isEmpty()) {
			List<Subentity> subentities = new ArrayList<Subentity>();
			// Extra fields
			String fieldName = "correspondingAuthor";
//			List<String> fields = new ArrayList<String>();
//			fields.add("id");
//			entity.getFields().add(fieldName+"Id");
			Subentity subentity = new Subentity();
			subentity.setFieldName(fieldName);
			Map<String, String> filters = new HashMap<>();
			filters.put("id", filter.getDirectedBy());
			subentity.setFilters(filters);
			subentities.add(subentity);
			entity.setSubentities(subentities);
		}

		// Add data to subentity attributes and filters
//		if (filter.getOrganizationId()!=null && !filter.getOrganizationId().isEmpty()) {
//			List<Subentity> subentities = new ArrayList<Subentity>();
//			// Extra fields
//			String fieldName = "correspondingOrganization";
////			List<String> fields = new ArrayList<String>();
////			fields.add("id");
////			entity.getFields().add(fieldName+"Id");
//			Subentity subentity = new Subentity();
//			subentity.setFieldName(fieldName);
//			Map<String, String> filters = new HashMap<>();
//			filters.put("id", filter.getOrganizationId());
//			subentity.setFilters(filters);
//			subentities.add(subentity);
//			entity.setSubentities(subentities);
//		}

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

		return new Entity("AcademicPublication", types, "date", "doi", "endPage", "id", "placeOfPublication",
				"publishedIn", "startPage", "summary", "title", "nowhere:type");
	}

	@Override
	public Entity retrieveEntity() {
		throw new NotImplementedException("retrieveEntity: Not implemented method");
	}

	@Override
	public List<Object> find(String id, String type) {
		logger.info("Searching academic publication with id: {} type: {}", id, type);

		SimpleQuery query = new SimpleQuery(this.retrieveEntity(type), filtersChunk(id));

		return serviceSPARQL.run(query);
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
}
