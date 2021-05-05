package es.um.asio.service.service.booksection.impl;

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

import es.um.asio.service.filter.booksection.BookSectionFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.model.PageableQuery;
import es.um.asio.service.model.Subentity;
import es.um.asio.service.service.booksection.BookSectionService;
import es.um.asio.service.service.impl.FusekiService;
import es.um.asio.service.service.sparql.SparqlExecQuery;

@Service
public class BookSectionServiceImpl extends FusekiService<BookSectionFilter> implements BookSectionService {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(BookSectionServiceImpl.class);

	@Autowired
	private SparqlExecQuery serviceSPARQL;
	
	@Override
	public Page<FusekiResponse> findPaginated(BookSectionFilter filter, Pageable pageable) {
		logger.info("Searching books with filter: {} page: {}", filter, pageable);

		PageableQuery pageableQuery = new PageableQuery(this.retrieveEntity(), filtersChunk(filter), pageable);

		return serviceSPARQL.run(pageableQuery);
	}

	@Override
	public String filtersChunk(BookSectionFilter filter) {
		StringBuilder strBuilder = new StringBuilder();
		
		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getDate())) {
				strBuilder.append("FILTER (?date = \"");
				strBuilder.append(filter.getDate());
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
			
			if (StringUtils.isNotBlank(filter.getEdition())) {
				strBuilder.append("FILTER (?edition = \"");
				strBuilder.append(filter.getEdition());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}
			
			if (StringUtils.isNotBlank(filter.getEndPage())) {
				strBuilder.append("FILTER (?endPage = \"");
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
			
			if (StringUtils.isNotBlank(filter.getPlaceOfPublication())) {
				strBuilder.append("FILTER (?placeOfPublication = \"");
				strBuilder.append(filter.getPlaceOfPublication());
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

			if (StringUtils.isNotBlank(filter.getTitle())) {
				strBuilder.append("FILTER (LANG(?title) = \"");
				strBuilder.append(filter.getLanguage().substring(1));
				strBuilder.append("\") . ");
				strBuilder.append("FILTER ( regex(?title, \"");
				strBuilder.append(filter.getTitle());
				strBuilder.append("\", \"i\")) . ");
			}
			
			if (StringUtils.isNotBlank(filter.getBookId())) {
				strBuilder.append("FILTER (?hasPublicationVenueId  = \"");
				strBuilder.append(filter.getBookId());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}
		}
		
		return strBuilder.toString();
	}

	@Override
	public Entity retrieveEntity(BookSectionFilter filter) {
		Entity entity = new Entity("BookSection", "date", "doi", "edition", "endPage", "id", "placeOfPublication", "startPage", "title");
		
		// Add data to subentity atributes and filters
		if (filter.getBookId()!=null && !filter.getBookId().isEmpty()) {
			List<Subentity> subentities = new ArrayList<Subentity>();
			// Extra fields
			String fieldName = "hasPublicationVenueId";
//			List<String> fields = new ArrayList<String>();
//			fields.add("testid");
//			entity.setFields(fields);
//			entity.getFields().add(fieldName+"Id");
			Subentity subentity = new Subentity();
			subentity.setFieldName(fieldName);
			Map<String, String> filters = new HashMap<>();
			filters.put("id", filter.getBookId());
			subentity.setFilters(filters);
			subentities.add(subentity);
			entity.setSubentities(subentities);
		}
		
		return entity;
	}

	@Override
	public Entity retrieveEntity() {
		// TODO Auto-generated method stub
		return null;
	}
}
