package es.um.asio.service.service.document.impl;

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

import es.um.asio.service.filter.document.DocumentFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.model.PageableQuery;
import es.um.asio.service.model.SimpleQuery;
import es.um.asio.service.model.Subentity;
import es.um.asio.service.service.document.DocumentService;
import es.um.asio.service.service.impl.FusekiService;
import es.um.asio.service.service.sparql.SparqlExecQuery;

@Service
public class DocumentServiceImpl extends FusekiService<DocumentFilter> implements DocumentService {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);

	@Autowired
	private SparqlExecQuery serviceSPARQL;

	@Override
	public Page<FusekiResponse> findPaginated(DocumentFilter filter, Pageable pageable) {
		logger.info("Searching documents with filter: {} page: {}", filter, pageable);

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
	public String filtersChunk(DocumentFilter filter) {
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
	public String filtersChunk(String id) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("FILTER (regex(?id, \"^");
		strBuilder.append(id);
		strBuilder.append("$\")) . ");

		return strBuilder.toString();
	}

	/**
	 * Retrieve the document entity.
	 *
	 * @param filter the filter
	 * @return the entity
	 */
	@Override
	public Entity retrieveEntity(DocumentFilter filter) {
		List<String> types = StringUtils.isNotBlank(filter.getTypes()) ? Arrays.asList(filter.getTypes().split(","))
				: Arrays.asList("Article", "Book");

		Entity entity = new Entity("Documento", types, "date", "doi", "id", "title", "nowhere:type", "freetext:(?x AS ?uri)");

		// Add data to subentity atributes and filters
		if (filter.getAuthorId() != null && !filter.getAuthorId().isEmpty()) {
			List<Subentity> subentities = new ArrayList<Subentity>();
			// Extra fields
			String fieldName = "correspondingAuthor";
//			List<String> fields = new ArrayList<String>();
//			fields.add("testid");
//			entity.setFields(fields);
//			entity.getFields().add(fieldName+"Id");
			Subentity subentity = new Subentity();
			subentity.setFieldName(fieldName);
			Map<String, String> filters = new HashMap<>();
			filters.put("id", filter.getAuthorId());
			subentity.setFilters(filters);
			subentities.add(subentity);
			entity.setSubentities(subentities);
		}

		// Add data to subentity atributes and filters
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

		return entity;
	}

	/**
	 * Retrieve document detail entity.
	 *
	 * @param type the type
	 * @return the entity
	 */
	@Override
	public Entity retrieveEntity(String type) {
		List<String> types = new ArrayList<String>();
		String[] splitType = type.split("/");
		types.add(splitType[splitType.length - 1]);

		if (type.equals("Article")) {
			return new Entity("Documento", types, "date", "doi", "endPage", "id", "publishedIn", "startPage", "title",
					"summary", "nowhere:type");
		} else if (type.equals("Book")) {
			return new Entity("Documento", types, "date", "doi", "edition", "endPage", "iccn", "id",
					"placeOfPublication", "publishedIn", "startPage", "summary", "title", "nowhere:type");
		} else {
			return new Entity("Documento", types, "date", "doi", "endPage", "id", "publishedIn", "startPage", "title",
					"nowhere:type");
		}
	}

	@Override
	public Entity retrieveEntity() {
		throw new NotImplementedException("retrieveEntity: Not implemented method");
	}

}
