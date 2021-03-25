package es.um.asio.service.service.statistcs.impl;

import org.springframework.stereotype.Service;

import es.um.asio.service.service.statistcs.StatisticsService;

@Service
public class StatisticsServiceImpl implements StatisticsService {

	@Override
	public String topPublications() {
		// TODO SPARQL DTO
		return "[{\"name\": \"Ciencias agrícolas y agroalimentarias\",\"value\": 10},\r\n"
				+ "			{\"name\": \"Agricultura y Bosques\",\"value\": 20}, \r\n"
				+ "			{\"name\": \"Astronomía y astrofísica\",\"value\": 15}, \r\n"
				+ "			{\"name\": \"Biomedicina\",\"value\": 30},\r\n"
				+ "			{\"name\": \"Economía\",\"value\": 30},\r\n"
				+ "			{\"name\": \"Ciencia y tecnología ambiental\",\"value\": 30},\r\n"
				+ "			{\"name\": \"Ciencia y tecnología de los alimentos\",\"value\": 30},\r\n"
				+ "			{\"name\": \"Física fundamental y de partículas\",\"value\": 30},\r\n"
				+ "			{\"name\": \"Producción industrial, ingeniería civil e ingeniería para la sociedad\",\"value\": 30},\r\n"
				+ "			{\"name\": \"Ciencias de la vida\",\"value\": 30},\r\n"
				+ "			{\"name\": \"Ciencias matemáticas\",\"value\":30},\r\n"
				+ "			{\"name\": \"Biología molecular y celular\",\"value\": 30}]";
	}

	@Override
	public String topPatents() {
		// TODO SPARQL DTO
		return "[{\"name\": \"Ciencias agrícolas y agroalimentarias\",\"value\": 10},\r\n"
				+ "			{\"name\": \"Agricultura y Bosques\",\"value\": 20}, \r\n"
				+ "			{\"name\": \"Astronomía y astrofísica\",\"value\": 15}, \r\n"
				+ "			{\"name\": \"Biomedicina\",\"value\": 30},\r\n"
				+ "			{\"name\": \"Economía\",\"value\": 30},\r\n"
				+ "			{\"name\": \"Ciencia y tecnología ambiental\",\"value\": 30},\r\n"
				+ "			{\"name\": \"Ciencia y tecnología de los alimentos\",\"value\": 30},\r\n"
				+ "			{\"name\": \"Física fundamental y de partículas\",\"value\": 30},\r\n"
				+ "			{\"name\": \"Producción industrial, ingeniería civil e ingeniería para la sociedad\",\"value\": 30},\r\n"
				+ "			{\"name\": \"Ciencias de la vida\",\"value\": 30},\r\n"
				+ "			{\"name\": \"Ciencias matemáticas\",\"value\":30},\r\n"
				+ "			{\"name\": \"Biología molecular y celular\",\"value\": 30}]";
	}

}
