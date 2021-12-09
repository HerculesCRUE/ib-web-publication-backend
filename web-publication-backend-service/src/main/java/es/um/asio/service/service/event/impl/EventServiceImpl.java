package es.um.asio.service.service.event.impl;

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

import es.um.asio.service.filter.event.EventFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.model.PageableQuery;
import es.um.asio.service.model.SimpleQuery;
import es.um.asio.service.model.Subentity;
import es.um.asio.service.service.event.EventService;
import es.um.asio.service.service.impl.FusekiService;
import es.um.asio.service.service.sparql.SparqlExecQuery;

@Service
public class EventServiceImpl extends FusekiService<EventFilter> implements EventService {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

	@Autowired
	private SparqlExecQuery serviceSPARQL;

	@Override
	public Page<FusekiResponse> findPaginated(EventFilter filter, Pageable pageable) {
		logger.info("Searching events with filter: {} page: {}", filter, pageable);

		PageableQuery pageableQuery = new PageableQuery(this.retrieveEntity(filter), filtersChunk(filter), pageable);

		return serviceSPARQL.run(pageableQuery);
	}

	@Override
	public List<Object> find(String id, String type) {
		logger.info("Searching event with id: {} type: {}", id, type);

		SimpleQuery query = new SimpleQuery(this.retrieveEntity(type), filtersChunk(id));

		return serviceSPARQL.run(query);
	}

	@Override
	public String filtersChunk(EventFilter filter) {
		StringBuilder strBuilder = new StringBuilder();

		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getDate())) {
				strBuilder.append("FILTER regex(?date, \"");
				strBuilder.append(filter.getDate());
				strBuilder.append("\",\"i\"");				
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
	 * Retrieve the event entity.
	 *
	 * @param filter the filter
	 * @return the entity
	 */
	@Override
	public Entity retrieveEntity(EventFilter filter) {
		List<String> types = StringUtils.isNotBlank(filter.getTypes()) ? Arrays.asList(filter.getTypes().split(","))
				: Arrays.asList("Conference", "Exhibit", "Activity");

		Entity entity = new Entity("Evento", types, "date", "id", "locality", "title", "nowhere:type", "freetext:(?x AS ?uri)");

		if (StringUtils.isNotBlank(filter.getParticipantId())) {
			List<Subentity> subentities = new ArrayList<Subentity>();

			String fieldName = "participatedBy";

			Map<String, String> filters = new HashMap<>();
			filters.put("id", filter.getParticipantId());
			Subentity subentity = new Subentity();
			subentity.setFieldName(fieldName);
			subentity.setFilters(filters);
			subentities.add(subentity);
			entity.setSubentities(subentities);
		}

		return entity;
	}

	/**
	 * Retrieve event detail entity.
	 *
	 * @param type the type
	 * @return the entity
	 */
	@Override
	public Entity retrieveEntity(String type) {
		List<String> types = new ArrayList<String>();
		String[] splitType = type.split("/");
		types.add(splitType[splitType.length - 1]);

		return new Entity("Evento", types, "date", "id", "locality", "title", "nowhere:type");

	}

	@Override
	public Entity retrieveEntity() {
		throw new NotImplementedException("retrieveEntity: Not implemented method");
	}

}
