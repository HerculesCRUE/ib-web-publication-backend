package es.um.asio.back.controller.memberrole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.MemberRoleDto;
import es.um.asio.service.filter.memberrole.MemberRoleFilter;
import es.um.asio.service.proxy.memberrole.MemberRolePoxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(MemberRoleController.Mappings.BASE)
public class MemberRoleController {

	@Autowired
	private MemberRolePoxy proxy;
	
	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;


	@GetMapping(MemberRoleController.Mappings.SEARCH)
	public Page<MemberRoleDto> searchProyects(final MemberRoleFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/memberRole";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";
	}
}