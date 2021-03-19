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
