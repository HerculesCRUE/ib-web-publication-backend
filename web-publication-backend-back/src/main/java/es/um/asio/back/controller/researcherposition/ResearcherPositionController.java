package es.um.asio.back.controller.researcherposition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.ResearcherPositionDto;
import es.um.asio.service.filter.researcherposition.ResearcherPositionFilter;
import es.um.asio.service.proxy.researcherposition.ResearcherPositionProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(ResearcherPositionController.Mappings.BASE)
public class ResearcherPositionController {

	@Autowired
	private ResearcherPositionProxy proxy;
	
	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;


	@GetMapping(ResearcherPositionController.Mappings.SEARCH)
	public Page<ResearcherPositionDto> searchProyects(final ResearcherPositionFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/researcherposition";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";
	}
}
