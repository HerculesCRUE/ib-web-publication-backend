package es.um.asio.back.controller.statistics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(StatisticsController.Mappings.BASE)
public class StatisticsController {

	private String mockJson = "[{\"name\": \"Ciencias agrícolas y agroalimentarias\",\"value\": 10},\r\n"
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

	@GetMapping(StatisticsController.Mappings.TOP_PUBLICATIONS)
	public String topPublications() {
		return this.mockJson;
	}

	@GetMapping(StatisticsController.Mappings.TOP_PATENTS)
	public String topPatents() {
		return this.mockJson;
	}

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/statistics";

		/**
		 * Graphics investigation actions.
		 */
		protected static final String TOP_PATENTS = "/topPatents";

		/**
		 * Graphics investigation actions.
		 */
		protected static final String TOP_PUBLICATIONS = "/topPublications";
	}

}
