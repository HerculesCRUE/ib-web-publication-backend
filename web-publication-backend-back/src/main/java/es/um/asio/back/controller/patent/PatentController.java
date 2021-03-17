package es.um.asio.back.controller.patent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.PatentDto;
import es.um.asio.service.filter.patent.PatentFilter;
import es.um.asio.service.proxy.patent.PatentProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Patent controller.
 */
@RestController
@RequestMapping(PatentController.Mappings.BASE)
public class PatentController {

	@Autowired
	private PatentProxy proxy;

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

	@GetMapping(PatentController.Mappings.SEARCH)
	public Page<PatentDto> searchProyects(final PatentFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}

	@GetMapping(PatentController.Mappings.AREA)
	public String area() {
		return this.mockJson;
	}

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/patent";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";

		/**
		 * Graphics area.
		 */
		protected static final String AREA = "/area";
	}

}
