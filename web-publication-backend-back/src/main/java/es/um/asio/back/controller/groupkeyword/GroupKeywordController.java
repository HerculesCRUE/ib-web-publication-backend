package es.um.asio.back.controller.groupkeyword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.GroupKeywordDto;
import es.um.asio.service.filter.groupkeyword.GroupKeywordFilter;
import es.um.asio.service.proxy.gorupkeyword.GroupKeywordProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(GroupKeywordController.Mappings.BASE)
public class GroupKeywordController {

	@Autowired
	private GroupKeywordProxy proxy;
	
	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;


	@GetMapping(GroupKeywordController.Mappings.SEARCH)
	public Page<GroupKeywordDto> searchProyects(final GroupKeywordFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/groupKeyword";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";
	}
}