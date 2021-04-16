package es.um.asio.back.controller.scientificpublication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.ScientificPublicationDto;
import es.um.asio.service.dto.graphic.GraphicsDto;
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
	public Page<ScientificPublicationDto> searchProyects(final ScientificPublicationFilter filter,
			final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}

	@GetMapping(ScientificPublicationController.Mappings.GET)
	public ScientificPublicationDto findPatent(@PathVariable("id") final String id) {
		return this.proxy.find(id);
	}

	@GetMapping(ScientificPublicationController.Mappings.PUBLICATION_BY_PERSON)
	public List<GraphicsDto> publicationByPerson(@PathVariable("id") final String id) {
		return this.proxy.publicationByPerson(id);
	}

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {

		protected static final String PUBLICATION_BY_PERSON = "/publicationByPerson/{id}";

		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/scientificpublication";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";

		/**
		 * Mapping for get.
		 */
		protected static final String GET = "/{id}";
	}
}
