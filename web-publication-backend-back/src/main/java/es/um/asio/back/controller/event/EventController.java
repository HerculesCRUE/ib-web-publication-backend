package es.um.asio.back.controller.event;

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
import es.um.asio.service.dto.EventDto;
import es.um.asio.service.filter.event.EventFilter;
import es.um.asio.service.proxy.event.EventProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Event controller.
 */
@RestController
@RequestMapping(EventController.Mappings.BASE)
public class EventController {

	@Autowired
	private EventProxy proxy;
	
	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;
	


	@GetMapping(EventController.Mappings.SEARCH)
	public Page<EventDto> searchEvents(final EventFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}
	
	@GetMapping(EventController.Mappings.GET)
	public EventDetailDto findEvent(@PathVariable("id") final String id, @PathVariable("type") final String type) {
		byte[] decodedBytes = Base64.getDecoder().decode(type);
		String decodedString = new String(decodedBytes);
		return this.proxy.find(id, decodedString);
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/event";

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
