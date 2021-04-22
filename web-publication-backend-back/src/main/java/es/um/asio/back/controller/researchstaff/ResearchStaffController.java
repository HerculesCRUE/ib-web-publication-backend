package es.um.asio.back.controller.researchstaff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.PersonDto;
import es.um.asio.service.filter.researchstaff.ResearchStaffFilter;
import es.um.asio.service.proxy.researchstaff.ResearchStaffProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * ResearchStaff controller.
 */
@RestController
@RequestMapping(ResearchStaffController.Mappings.BASE)
public class ResearchStaffController {

	@Autowired
	private ResearchStaffProxy proxy;
	
	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;


	@GetMapping(ResearchStaffController.Mappings.SEARCH)
	public Page<PersonDto> searchResearchStaff(final ResearchStaffFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/researchstaff";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";
	}
}
