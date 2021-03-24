package es.um.asio.back.controller.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.PersonDto;
import es.um.asio.service.filter.person.PersonFilter;
import es.um.asio.service.proxy.person.PersonProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Person controller.
 */
@RestController
@RequestMapping(PersonController.Mappings.BASE)
public class PersonController {

	@Autowired
	private PersonProxy proxy;

	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;

	private String mockJson = "{\r\n" + "    \"legendData\": [\r\n" + "        \"Verificación 1\",\r\n"
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

	@GetMapping(PersonController.Mappings.SEARCH)
	public Page<PersonDto> searchProyects(final PersonFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}

	@GetMapping(PersonController.Mappings.AREA)
	public String area() {
		return this.mockJson;
	}
	
	@GetMapping(PersonController.Mappings.GET)
	public PersonDto findPerson(@PathVariable("id") final String id) {
		return this.proxy.find(id);
	}

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/person";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";
		
		/**
		 * Mapping for get.
		 */
		protected static final String GET = "/{id}";

		/**
		 * Graphics area.
		 */
		protected static final String AREA = "/area";
	}
}
