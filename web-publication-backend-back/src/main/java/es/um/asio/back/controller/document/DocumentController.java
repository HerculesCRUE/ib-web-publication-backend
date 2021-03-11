package es.um.asio.back.controller.document;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.DocumentDetailDto;
import es.um.asio.service.dto.DocumentDto;
import es.um.asio.service.filter.document.DocumentFilter;
import es.um.asio.service.proxy.document.DocumentProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Document controller.
 */
@RestController
@RequestMapping(DocumentController.Mappings.BASE)
public class DocumentController {

	@Autowired
	private DocumentProxy proxy;
	
	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;


	@GetMapping(DocumentController.Mappings.SEARCH)
	public Page<DocumentDto> searchDocuments(final DocumentFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}
	
	@GetMapping(DocumentController.Mappings.GET)
	public DocumentDetailDto findDocument(@PathVariable("id") final String id, @PathVariable("type") final String type) {
		byte[] decodedBytes = Base64.getDecoder().decode(type);
		String decodedString = new String(decodedBytes);
		return this.proxy.find(id,decodedString);
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/document";

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
