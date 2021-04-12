package es.um.asio.back.controller.phdsupervisingrelationship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.PhdSupervisingRelationshipDto;
import es.um.asio.service.filter.phdsupervisingrelationship.PhdSupervisingRelationshipFilter;
import es.um.asio.service.proxy.phdsupervisingrelationship.PhdSupervisingRelationshipProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(PhdSupervisingRelationshipController.Mappings.BASE)
public class PhdSupervisingRelationshipController {

	@Autowired
	private PhdSupervisingRelationshipProxy proxy;
	
	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;


	@GetMapping(PhdSupervisingRelationshipController.Mappings.SEARCH)
	public Page<PhdSupervisingRelationshipDto> searchProyects(final PhdSupervisingRelationshipFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/phdsupervisingrelationship";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";
	}
}
