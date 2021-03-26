package es.um.asio.back.controller.supervisorrole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.SupervisorRoleDto;
import es.um.asio.service.filter.supervisorrole.SupervisorRoleFilter;
import es.um.asio.service.proxy.supervisorrole.SupervisorRoleProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(SupervisorRoleController.Mappings.BASE)
public class SupervisorRoleController {

	@Autowired
	private SupervisorRoleProxy proxy;
	
	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;


	@GetMapping(SupervisorRoleController.Mappings.SEARCH)
	public Page<SupervisorRoleDto> searchProyects(final SupervisorRoleFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/supervisorRole";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";
	}
}