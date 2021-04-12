package es.um.asio.back.controller.scientificpublication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.ScientificPublicationDto;
import es.um.asio.service.filter.scientificpublication.ScientificPublicationFilter;
import es.um.asio.service.proxy.scientificpublication.ScientificPublicationProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(ScientificPublicationController.Mappings.BASE)
public class ScientificPublicationController {

	@Autowired
	private ScientificPublicationProxy proxy;
	
	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;


	@GetMapping(ScientificPublicationController.Mappings.SEARCH)
	public Page<ScientificPublicationDto> searchProyects(final ScientificPublicationFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/scientificpublication";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";
	}
}
