package es.um.asio.service.service.university.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.filter.university.UniversityFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.model.PageableQuery;
import es.um.asio.service.model.SimpleQuery;
import es.um.asio.service.service.article.impl.ArticleServiceImpl;
import es.um.asio.service.service.impl.FusekiService;
import es.um.asio.service.service.sparql.SparqlExecQuery;
import es.um.asio.service.service.university.UniversityService;

@Service
public class UniversityServiceImpl extends FusekiService<UniversityFilter> implements UniversityService {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

	@Autowired
	private SparqlExecQuery serviceSPARQL;

	@Override
	public Page<FusekiResponse> findPaginated(UniversityFilter filter, Pageable pageable) {
		logger.info("Searching universities with filter: {} page: {}", filter, pageable);

		PageableQuery pageableQuery = new PageableQuery(this.retrieveEntity(), filtersChunk(filter), pageable);

		return serviceSPARQL.run(pageableQuery);
	}

	@Override
	public String filtersChunk(UniversityFilter filter) {
		StringBuilder strBuilder = new StringBuilder();

		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getId())) {
				strBuilder.append("FILTER (regex(?id, \"^");
				strBuilder.append(filter.getId());
				strBuilder.append("$\")) . ");
			}

			if (StringUtils.isNotBlank(filter.getTitle())) {
				strBuilder.append("FILTER (LANG(?title) = \"");
				strBuilder.append(filter.getLanguage().substring(1));
				strBuilder.append("\") . ");
				strBuilder.append("FILTER ( regex(?name, \"");
				strBuilder.append(filter.getTitle());
				strBuilder.append("\", \"i\")) . ");
			}
		}

		return strBuilder.toString();
	}

	@Override
	public Entity retrieveEntity() {
		return new Entity("University", "id", "title", "abbreviation", "description", "keyword");
	}

	@Override
	public String getQualitySeal() {
		// TODO SPARQL DTO
		return "{\r\n" + "    \"legendData\": [\r\n" + "        \"Verificación 1\",\r\n"
				+ "        \"Acreditación 1\",\r\n" + "        \"Acreditación de las dimensiones adicionales 1\",\r\n"
				+ "        \"Certificación de garantía interna de calidad (SGIC) 1\",\r\n"
				+ "        \"Centro acreditado institucionalmente 1\"\r\n" + "    ],\r\n" + "    \"seriesData\": [\r\n"
				+ "        {\r\n" + "            \"name\": \"Verificación 1\",\r\n"
				+ "            \"value\": 3936420535\r\n" + "        },\r\n" + "        {\r\n"
				+ "            \"name\": \"Acreditación 1\",\r\n" + "            \"value\": 2548317238\r\n"
				+ "        },\r\n" + "        {\r\n"
				+ "            \"name\": \"Acreditación de las dimensiones adicionales 1\",\r\n"
				+ "            \"value\": 495368615\r\n" + "        },\r\n" + "        {\r\n"
				+ "            \"name\": \"Certificación de garantía interna de calidad (SGIC) 1\",\r\n"
				+ "            \"value\": 2728792142\r\n" + "        },\r\n" + "        {\r\n"
				+ "            \"name\": \"Centro acreditado institucionalmente 1\",\r\n"
				+ "            \"value\": 240079264\r\n" + "        }\r\n" + "    ],\r\n" + "    \"selected\": {\r\n"
				+ "        \"Verificación 1\": true,\r\n" + "        \"Acreditación 1\": true,\r\n"
				+ "        \"Acreditación de las dimensiones adicionales 1\": true,\r\n"
				+ "        \"Certificación de garantía interna de calidad (SGIC) 1\": true,\r\n"
				+ "        \"Centro acreditado institucionalmente 1\": true\r\n" + "    }\r\n" + "}";
	}

	@Override
	public List<Object> organizationByType() {
		SimpleQuery query = new SimpleQuery(this.retrieveGraphicEntity(), "");

		return serviceSPARQL.runCount(query);
	}

	private Entity retrieveGraphicEntity() {
		List<String> types = Arrays.asList("Organization", "University", "FundingOrganization");

		Entity entity = new Entity("Organization", types, "nowhere:type");

		List<String> groups = new ArrayList<>();
		groups.add("type");
		entity.setGroup(groups);

		return entity;
	}

}
