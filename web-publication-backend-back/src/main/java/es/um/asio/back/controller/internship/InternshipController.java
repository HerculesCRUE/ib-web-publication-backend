package es.um.asio.back.controller.internship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.InternshipDto;
import es.um.asio.service.filter.internship.InternshipFilter;
import es.um.asio.service.proxy.internship.InternshipProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(InternshipController.Mappings.BASE)
public class InternshipController {

	@Autowired
	private InternshipProxy proxy;
	
	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;


	@GetMapping(InternshipController.Mappings.SEARCH)
	public Page<InternshipDto> searchProyects(final InternshipFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/internship";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";
	}
}