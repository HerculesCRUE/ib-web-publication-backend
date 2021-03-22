package es.um.asio.back.controller.organization;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.EventDetailDto;
import es.um.asio.service.dto.OrganizationDetailDto;
import es.um.asio.service.dto.OrganizationDto;
import es.um.asio.service.filter.organization.OrganizationFilter;
import es.um.asio.service.proxy.organization.OrganizationProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Organization controller.
 */
@RestController
@RequestMapping(OrganizationController.Mappings.BASE)
public class OrganizationController {

	@Autowired
	private OrganizationProxy proxy;
	
	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;


	@GetMapping(OrganizationController.Mappings.SEARCH)
	public Page<OrganizationDto> searchProyects(final OrganizationFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}
	
	@GetMapping(OrganizationController.Mappings.GET)
	public OrganizationDetailDto findOrganization(@PathVariable("id") final String id, @PathVariable("type") final String type) {
		byte[] decodedBytes = Base64.getDecoder().decode(type);
		String decodedString = new String(decodedBytes);
//		return this.proxy.find(id,decodedString);
		return createMockDetailDto();
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/organization";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";
		

		/**
         * Mapping for get.
         */
        protected static final String GET = "/{id}/{type}";
	}
	

	private OrganizationDetailDto createMockDetailDto() {
		OrganizationDetailDto mock = new OrganizationDetailDto();
		mock.setId("123");
		mock.setAbbreviation("Universidad Pompeu Fabra");
		mock.setDescription("Universidad Pompeu Fabra");
		mock.setLocatedIn("Barcelona");
		mock.setTitle("Universidad Pompeu Fabra");
		mock.setContains("2Organización Universidad Pompeu Fabra");
		mock.setStartDate("09-01-1895 9:00");
		mock.setFreetextKeyword("University");
		mock.setHasContactInfo("info@upf.es");
		mock.setHomepage("www.upf.edu");
		
		return mock;
	}
}
