package es.um.asio.back.controller.academicpublication;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.back.controller.document.DocumentController;
import es.um.asio.service.dto.AcademicPublicationDetailDto;
import es.um.asio.service.dto.AcademicPublicationDto;
import es.um.asio.service.dto.DocumentDetailDto;
import es.um.asio.service.filter.academicpublication.AcademicPublicationFilter;
import es.um.asio.service.proxy.academicpublication.AcademicPublicationProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * AcademicPublication controller.
 */
@RestController
@RequestMapping(AcademicPublicationController.Mappings.BASE)
public class AcademicPublicationController {

	@Autowired
	private AcademicPublicationProxy proxy;
	
	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;


	@GetMapping(AcademicPublicationController.Mappings.SEARCH)
	public Page<AcademicPublicationDto> searchProyects(final AcademicPublicationFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}
	
	@GetMapping(AcademicPublicationController.Mappings.GET)
	public AcademicPublicationDetailDto findAcademicPublication(@PathVariable("id") final String id, @PathVariable("type") final String type) {
		byte[] decodedBytes = Base64.getDecoder().decode(type);
		String decodedString = new String(decodedBytes);
		return this.proxy.find(id,decodedString);
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/academicpublication";

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
