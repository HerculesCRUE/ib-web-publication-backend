package es.um.asio.service.service.area.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.um.asio.service.filter.area.AreaFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.SimpleQuery;
import es.um.asio.service.model.Subentity;
import es.um.asio.service.service.area.AreaService;
import es.um.asio.service.service.sparql.SparqlExecQuery;

@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	private SparqlExecQuery serviceSPARQL;

	@Override
	public List<Object> getAreaByresearchGroup() {

		SimpleQuery query = new SimpleQuery(this.retrieveGraphicEntity(), "");

		return serviceSPARQL.runCount(query);
	}

	private Entity retrieveGraphicEntity() {
		Entity entity = new Entity("Research-group", "nowhere:hasKnowledgeAreatitle");

		List<Subentity> subentities = new ArrayList<Subentity>();
		// Extra fields
		String fieldName = "hasKnowledgeArea";
		Subentity subentity = new Subentity();
		subentity.setFieldName(fieldName);

		// Add All
		subentities.add(subentity);
		entity.setSubentities(subentities);

		// Extra fields
		String fieldName2 = "title";
		Subentity subentity2 = new Subentity();
		subentity2.setFieldName(fieldName2);

		// Add All
		subentity.setSubentities(new ArrayList<Subentity>());
		subentity.getSubentities().add(subentity2);
		entity.setSubentities(subentities);

		List<String> groups = new ArrayList<>();
		groups.add("hasKnowledgeAreatitle");
		entity.setGroup(groups);

		return entity;
	}

	@Override
	public String getAreasWithMoreProjects(AreaFilter filter) {

		// SimpleQuery query = new SimpleQuery(this.retrieveEntity(),
		// filtersChunk(filter));

		// serviceSPARQL.run(query);

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

	private String filtersChunk(AreaFilter filter) {
		StringBuilder strBuilder = new StringBuilder();
		if (filter != null) {
			if (filter.getYear() != null) {
				strBuilder.append("FILTER (?date = \"");
				strBuilder.append(filter.getYear());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}
		}
		return strBuilder.toString();
	}

	private Entity retrieveEntity() {
		// TODO ENTITY
		return new Entity("Area");
	}

}
