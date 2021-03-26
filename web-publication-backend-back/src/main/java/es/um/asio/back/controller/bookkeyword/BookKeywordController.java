package es.um.asio.back.controller.bookkeyword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.BookKeywordDto;
import es.um.asio.service.filter.bookkeyword.BookKeywordFilter;
import es.um.asio.service.proxy.bookkeyword.BookKeywordProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(BookKeywordController.Mappings.BASE)
public class BookKeywordController {

	@Autowired
	private BookKeywordProxy proxy;
	
	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;


	@GetMapping(BookKeywordController.Mappings.SEARCH)
	public Page<BookKeywordDto> searchProyects(final BookKeywordFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/bookKeyword";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";
	}
}