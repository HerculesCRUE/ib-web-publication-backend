package es.um.asio.back.controller.booksectionkeyword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.BookSectionKeywordDto;
import es.um.asio.service.filter.booksectionkeyword.BookSectionKeywordFilter;
import es.um.asio.service.proxy.booksectionkeyword.BookSectionKeywordProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(BookSectionKeywordController.Mappings.BASE)
public class BookSectionKeywordController {

	@Autowired
	private BookSectionKeywordProxy proxy;
	
	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;


	@GetMapping(BookSectionKeywordController.Mappings.SEARCH)
	public Page<BookSectionKeywordDto> searchProyects(final BookSectionKeywordFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/bookSectionKeyword";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";
	}
}