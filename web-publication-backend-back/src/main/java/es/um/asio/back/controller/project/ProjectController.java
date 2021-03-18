package es.um.asio.back.controller.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.ProjectDto;
import es.um.asio.service.filter.project.ProjectFilter;
import es.um.asio.service.proxy.project.ProjectProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Project controller.
 */
@RestController
@RequestMapping(ProjectController.Mappings.BASE)
public class ProjectController {

	@Autowired
	private ProjectProxy proxy;

	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;

	private String mockJson = "[{\r\n" + "    \"name\": 'Ciencias agrícolas y agroalimentarias',\r\n"
			+ "    \"value\": 10\r\n" + "}, {\r\n" + "    \"name\": 'Agricultura y Bosques',\r\n"
			+ "    \"value\": 20\r\n" + "}, {\r\n" + "    \"name\": 'Astronomía y astrofísica',\r\n"
			+ "    \"value\": 15\r\n" + "}, {\r\n" + "    \"name\": 'Biomedicina',\r\n" + "    \"value\": 30\r\n"
			+ "}, {\r\n" + "    \"name\": 'Economía',\r\n" + "    \"value\": 30\r\n" + "}, {\r\n"
			+ "    \"name\": 'Ciencia y tecnología ambiental',\r\n" + "    \"value\": 30\r\n" + "}, {\r\n"
			+ "    \"name\": 'Ciencia y tecnología de los alimentos',\r\n" + "    \"value\": 30\r\n" + "}, {\r\n"
			+ "    \"name\": 'Física fundamental y de partículas',\r\n" + "    \"value\": 30\r\n" + "}, {\r\n"
			+ "    \"name\": 'Producción industrial, ingeniería civil e ingeniería para la sociedad',\r\n"
			+ "    \"value\": 30\r\n" + "}, {\r\n" + "    \"name\": 'Ciencias de la vida',\r\n"
			+ "    \"value\": 30\r\n" + "}, {\r\n" + "    \"name\": 'Ciencias matemáticas',\r\n"
			+ "    \"value\": 30\r\n" + "}, {\r\n" + "    \"name\": 'Biología molecular y celular',\r\n"
			+ "    \"value\": 30}\r\n" + "]";

	@GetMapping(ProjectController.Mappings.SEARCH)
	public Page<ProjectDto> searchProyects(final ProjectFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}

	@GetMapping(ProjectController.Mappings.INVESTIGATION)
	public String area() {
		return this.mockJson;
	}

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/project";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";

		/**
		 * Graphics investigation actions.
		 */
		protected static final String INVESTIGATION = "/investigation";
	}
}
