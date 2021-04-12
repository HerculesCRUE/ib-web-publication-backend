package es.um.asio.back.controller.awardeddegree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.AwardedDegreeDto;
import es.um.asio.service.filter.awardeddegree.AwardedDegreeFilter;
import es.um.asio.service.proxy.awardeddegree.AwardedDegreeProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(AwardedDegreeController.Mappings.BASE)
public class AwardedDegreeController {

	@Autowired
	private AwardedDegreeProxy proxy;
	
	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;


	@GetMapping(AwardedDegreeController.Mappings.SEARCH)
	public Page<AwardedDegreeDto> searchProyects(final AwardedDegreeFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/awardeddegree";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";
	}
}
