package es.um.asio.back.controller.principalinvestigatorrole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.PrincipalInvestigatorRoleDto;
import es.um.asio.service.filter.principalinvestigatorrole.PrincipalInvestigatorRoleFilter;
import es.um.asio.service.proxy.principalinvestigatorrole.PrincipalInvestigatorRoleProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(PrincipalInvestigatorRoleController.Mappings.BASE)
public class PrincipalInvestigatorRoleController {

	@Autowired
	private PrincipalInvestigatorRoleProxy proxy;
	
	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;


	@GetMapping(PrincipalInvestigatorRoleController.Mappings.SEARCH)
	public Page<PrincipalInvestigatorRoleDto> searchProyects(final PrincipalInvestigatorRoleFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/principalinvestigatorRole";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";
	}
}
