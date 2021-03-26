package es.um.asio.back.controller.conferencekeyword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.ConferenceKeywordDto;
import es.um.asio.service.filter.conferencekeyword.ConferenceKeywordFilter;
import es.um.asio.service.proxy.conferencekeyword.ConferenceKeywordProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(ConferenceKeywordController.Mappings.BASE)
public class ConferenceKeywordController {

	@Autowired
	private ConferenceKeywordProxy proxy;
	
	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;


	@GetMapping(ConferenceKeywordController.Mappings.SEARCH)
	public Page<ConferenceKeywordDto> searchProyects(final ConferenceKeywordFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/conferenceKeyword";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";
	}
}