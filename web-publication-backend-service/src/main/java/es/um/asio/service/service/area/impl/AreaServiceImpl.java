package es.um.asio.service.service.area.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.um.asio.service.filter.area.AreaFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.service.area.AreaService;
import es.um.asio.service.service.sparql.SparqlExecQuery;

@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	private SparqlExecQuery serviceSPARQL;

	@Override
	public String getAreaWithYear(AreaFilter filter) {

		// SimpleQuery query = new SimpleQuery(this.retrieveEntity(),
		// filtersChunk(filter));

		// serviceSPARQL.run(query);

		return "[{\"name\": \"Psicología\",\"value\": 10},\r\n"
				+ "			{\"name\": \"Métodos de análisis económico\",\"value\": 20}, \r\n"
				+ "			{\"name\": \"Microelectrónica nanotecnología y fotónica\",\"value\": 15}, \r\n"
				+ "			{\"name\": \"Materiales para la energía y el medioambiente\",\"value\": 30},\r\n"
				+ "			{\"name\": \"Materiales para biomedicina\",\"value\": 30},\r\n"
				+ "			{\"name\": \"Materiales estructurales\",\"value\": 30},\r\n"
				+ "			{\"name\": \"Materiales con funcionalidad eléctrica magnética óptica o térmica\",\"value\": 30},\r\n"
				+ "			{\"name\": \"Literatura filología lenguas y culturas antiguas y estudios culturales\",\"value\": 30},\r\n"
				+ "			{\"name\": \"Lingüística y lenguas\",\"value\": 30},\r\n"
				+ "			{\"name\": \"Investigación polar\",\"value\": 30},\r\n"
				+ "			{\"name\": \"Ciencias matemáticas\",\"value\":30},\r\n"
				+ "			{\"name\": \"Biología molecular y celular\",\"value\": 30}]";
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
