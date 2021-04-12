package es.um.asio.back.controller.curriculumvitae;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.CurriculumVitaeDto;
import es.um.asio.service.filter.curriculumvitae.CurriculumVitaeFilter;
import es.um.asio.service.proxy.curriculumvitae.CurriculumVitaeProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(CurriculumVitaeController.Mappings.BASE)
public class CurriculumVitaeController {
	
	@Autowired
	private CurriculumVitaeProxy proxy;
	
	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;


	@GetMapping(CurriculumVitaeController.Mappings.SEARCH)
	public Page<CurriculumVitaeDto> searchProyects(final CurriculumVitaeFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/curriculumvitae";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";
	}
}
