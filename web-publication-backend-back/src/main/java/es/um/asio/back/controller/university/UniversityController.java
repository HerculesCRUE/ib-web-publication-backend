package es.um.asio.back.controller.university;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.UniversityDto;
import es.um.asio.service.dto.graphic.GraphicsDto;
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

	@GetMapping(UniversityController.Mappings.SEARCH)
	public Page<UniversityDto> searchProyects(final UniversityFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}

	@GetMapping(UniversityController.Mappings.QUALITY_SEAL)
	public String qualitySeal() {
		return this.proxy.getQualitySeal();
	}

	@GetMapping(UniversityController.Mappings.ORGANIZATION_BY_TYPE)
	public List<GraphicsDto> organizationByType() {
		return this.proxy.organizationByType();
	}

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {

		protected static final String ORGANIZATION_BY_TYPE = "/organizationByType";

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
