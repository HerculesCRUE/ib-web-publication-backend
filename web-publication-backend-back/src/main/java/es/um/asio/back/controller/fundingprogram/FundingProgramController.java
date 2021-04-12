package es.um.asio.back.controller.fundingprogram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.FundingProgramDto;
import es.um.asio.service.filter.fundingprogram.FundingProgramFilter;
import es.um.asio.service.proxy.fundingprogram.FundingProgramProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(FundingProgramController.Mappings.BASE)
public class FundingProgramController {

	@Autowired
	private FundingProgramProxy proxy;
	
	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;


	@GetMapping(FundingProgramController.Mappings.SEARCH)
	public Page<FundingProgramDto> searchProyects(final FundingProgramFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/fundingprogram";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";
	}
}
