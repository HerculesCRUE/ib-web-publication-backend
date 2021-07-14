package es.um.asio.back.controller.ldp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.LdpEntityCountDto;
import es.um.asio.service.service.ldp.LdpService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(LdpControlller.Mappings.BASE)
public class LdpControlller {
	
	@Autowired
	private LdpService ldpService;
	
	@GetMapping(LdpControlller.Mappings.COUNTER)
	public Page<LdpEntityCountDto> ldpEntityCount(Pageable pageable) {		
		return ldpService.entityCount(pageable);
	}

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/ldp";
		
		/**
		 * Controller request mapping.
		 */
		protected static final String COUNTER = "/count";
	}
}
