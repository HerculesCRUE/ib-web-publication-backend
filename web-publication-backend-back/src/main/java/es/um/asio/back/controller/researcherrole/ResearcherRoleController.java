package es.um.asio.back.controller.researcherrole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.ResearcherRoleDto;
import es.um.asio.service.filter.researcherrole.ResearcherRoleFilter;
import es.um.asio.service.proxy.researcherrole.ResearcherRoleProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(ResearcherRoleController.Mappings.BASE)
public class ResearcherRoleController {

	@Autowired
	private ResearcherRoleProxy proxy;
	
	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;


	@GetMapping(ResearcherRoleController.Mappings.SEARCH)
	public Page<ResearcherRoleDto> searchProyects(final ResearcherRoleFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/researcherrole";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";
	}
}
