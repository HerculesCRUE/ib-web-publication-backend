package es.um.asio.back.controller.researchfield;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.ResearchFieldDto;
import es.um.asio.service.filter.researchfield.ResearchFieldFilter;
import es.um.asio.service.proxy.researchfield.ResearchFieldProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(ResearchFieldController.Mappings.BASE)
public class ResearchFieldController {

	@Autowired
	private ResearchFieldProxy proxy;
	
	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;


	@GetMapping(ResearchFieldController.Mappings.SEARCH)
	public Page<ResearchFieldDto> searchProyects(final ResearchFieldFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/researchField";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";
	}
}