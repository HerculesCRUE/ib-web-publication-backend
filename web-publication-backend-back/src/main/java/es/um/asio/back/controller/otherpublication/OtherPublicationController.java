package es.um.asio.back.controller.otherpublication;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.OtherPublicationDto;
import es.um.asio.service.filter.otherpublication.OtherPublicationFilter;
import es.um.asio.service.proxy.otherpublication.OtherPublicationProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * OtherPublication controller.
 */
@RestController
@RequestMapping(OtherPublicationController.Mappings.BASE)
public class OtherPublicationController {

	@Autowired
	private OtherPublicationProxy proxy;
	
	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;


	@GetMapping(OtherPublicationController.Mappings.SEARCH)
	public Page<OtherPublicationDto> searchOtherPublications(final OtherPublicationFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}
	
	@GetMapping(OtherPublicationController.Mappings.GET)
	public OtherPublicationDto findOtherPublication(@PathVariable("id") final String id, @PathVariable("type") final String type) {
		byte[] decodedBytes = Base64.getDecoder().decode(type);
		String decodedString = new String(decodedBytes);
		return this.proxy.find(id,decodedString);
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/otherpublication";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";

		/**
         * Mapping for get.
         */
        protected static final String GET = "/{id}/{type}";
	}
}
