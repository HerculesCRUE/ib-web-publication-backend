package es.um.asio.back.controller.gorup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.GroupDto;
import es.um.asio.service.filter.gorup.GroupFilter;
import es.um.asio.service.proxy.gorup.GroupProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(GroupController.Mappings.BASE)
public class GroupController {

	@Autowired
	private GroupProxy proxy;
	
	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;


	@GetMapping(GroupController.Mappings.SEARCH)
	public Page<GroupDto> searchProyects(final GroupFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/group";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";
	}
}