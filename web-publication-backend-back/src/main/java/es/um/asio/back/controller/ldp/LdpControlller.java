package es.um.asio.back.controller.ldp;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.LdpEntityCountDto;
import es.um.asio.service.dto.LdpEntityDetailsDto;
import es.um.asio.service.dto.LdpEntityRelatedDto;
import es.um.asio.service.dto.LdpSearchResultDto;
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
	
	@GetMapping(LdpControlller.Mappings.FIND_TITLE)
	public Page<LdpSearchResultDto> findByTitle(@RequestParam(required = true) String title, Pageable pageable) {
	    if(StringUtils.isBlank(title)) {
	        throw new IllegalArgumentException();
	    }
		
		return ldpService.findByTitleOrName(title, pageable);
	}
	
	@GetMapping(LdpControlller.Mappings.FIND_CATEGORY)
	public Page<LdpSearchResultDto> findByCategory(@RequestParam(required = true) String category, Pageable pageable) {
	    if(StringUtils.isBlank(category)) {
	        throw new IllegalArgumentException();
	    }
		
		return ldpService.findByCategory(category, pageable);
	}
	
	@GetMapping(LdpControlller.Mappings.FIND_DETAILS)
	public LdpEntityDetailsDto findEntityDetail(@RequestParam(required = true) String uri) {
		if (StringUtils.isBlank(uri)) {
			throw new IllegalArgumentException();
		}

		return ldpService.findDetails(uri);
	}
	
	@GetMapping(LdpControlller.Mappings.FIND_RELATED)
	public Page<LdpEntityRelatedDto> findEntityRelated(@RequestParam(required = true) String uri, Pageable pageable,@RequestParam(required = true) String type) {
		if (StringUtils.isBlank(uri)) {
			throw new IllegalArgumentException();
		}

		
		return ldpService.findRelated(uri, pageable, type);
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
		
		/**
		 * Controller request mapping.
		 */
		protected static final String FIND_TITLE = "/findTitle";
		
		/**
		 * Controller request mapping.
		 */
		protected static final String FIND_CATEGORY = "/findCategory";
		
		/**
		 * Controller request mapping.
		 */
		protected static final String FIND_DETAILS = "/findDetails";
		
		/**
		 * Controller request mapping.
		 */
		protected static final String FIND_RELATED = "/findRelated";
	}
}
