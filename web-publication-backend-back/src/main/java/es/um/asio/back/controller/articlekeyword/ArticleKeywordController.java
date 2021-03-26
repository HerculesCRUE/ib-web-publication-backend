package es.um.asio.back.controller.articlekeyword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.ArticleKeywordDto;
import es.um.asio.service.filter.articlekeyword.ArticleKeywordFilter;
import es.um.asio.service.proxy.articlekeyword.ArticleKeywordProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(ArticleKeywordController.Mappings.BASE)
public class ArticleKeywordController {

	@Autowired
	private ArticleKeywordProxy proxy;
	
	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;


	@GetMapping(ArticleKeywordController.Mappings.SEARCH)
	public Page<ArticleKeywordDto> searchProyects(final ArticleKeywordFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/articleKeyword";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";
	}
}