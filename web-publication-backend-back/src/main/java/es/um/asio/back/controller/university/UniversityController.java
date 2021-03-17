package es.um.asio.back.controller.university;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.UniversityDto;
import es.um.asio.service.filter.university.UniversityFilter;
import es.um.asio.service.proxy.university.UniversityProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * University controller.
 */
@RestController
@RequestMapping(UniversityController.Mappings.BASE)
public class UniversityController {

	@Autowired
	private UniversityProxy proxy;

	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;

	private String mockJson = "{\r\n" + "    \"legendData\": [\r\n" + "        \"Verificación 1\",\r\n"
			+ "        \"Acreditación 1\",\r\n" + "        \"Acreditación de las dimensiones adicionales 1\",\r\n"
			+ "        \"Certificación del sistema de garantía interna de la calidad (SGIC) de centro 1\",\r\n"
			+ "        \"Centro acreditado institucionalmente 1\"\r\n" + "    ],\r\n" + "    \"seriesData\": [\r\n"
			+ "        {\r\n" + "            \"name\": \"Verificación 1\",\r\n"
			+ "            \"value\": 3936420535\r\n" + "        },\r\n" + "        {\r\n"
			+ "            \"name\": \"Acreditación 1\",\r\n" + "            \"value\": 2548317238\r\n"
			+ "        },\r\n" + "        {\r\n"
			+ "            \"name\": \"Acreditación de las dimensiones adicionales 1\",\r\n"
			+ "            \"value\": 495368615\r\n" + "        },\r\n" + "        {\r\n"
			+ "            \"name\": \"Certificación del sistema de garantía interna de la calidad (SGIC) de centro 1\",\r\n"
			+ "            \"value\": 2728792142\r\n" + "        },\r\n" + "        {\r\n"
			+ "            \"name\": \"Centro acreditado institucionalmente 1\",\r\n"
			+ "            \"value\": 240079264\r\n" + "        }\r\n" + "    ],\r\n" + "    \"selected\": {\r\n"
			+ "        \"Verificación 1\": true,\r\n" + "        \"Acreditación 1\": true,\r\n"
			+ "        \"Acreditación de las dimensiones adicionales 1\": true,\r\n"
			+ "        \"Certificación del sistema de garantía interna de la calidad (SGIC) de centro 1\": true,\r\n"
			+ "        \"Centro acreditado institucionalmente 1\": true\r\n" + "    }\r\n" + "}";

	@GetMapping(UniversityController.Mappings.SEARCH)
	public Page<UniversityDto> searchProyects(final UniversityFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}

	@GetMapping(UniversityController.Mappings.QUALITY_SEAL)
	public String qualitySeal() {
		return this.mockJson;
	}

	@GetMapping(UniversityController.Mappings.FINANCING)
	public String financing() {
		return this.mockJson;
	}

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {

		protected static final String FINANCING = "/financing";

		protected static final String QUALITY_SEAL = "/quality";

		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/university";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";
	}
}
