package es.um.asio.back.controller.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.ProjectByModalityDto;
import es.um.asio.service.dto.ProjectDetailDto;
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

	@GetMapping(ProjectController.Mappings.SEARCH)
	public Page<ProjectDto> searchProyects(final ProjectFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}

	@GetMapping(ProjectController.Mappings.GET)
	public ProjectDetailDto findProject(@PathVariable("id") final String id) {
		return this.proxy.find(id);
	}

	@GetMapping(ProjectController.Mappings.BYMODALITY)
	public List<ProjectByModalityDto> getbyModality() {
		return this.proxy.getbyModality();
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
		 * Mapping for get.
		 */
		protected static final String GET = "/{id}";

		/**
		 * Graphics investigation actions.
		 */
		protected static final String BYMODALITY = "/byModality";
	}
}
