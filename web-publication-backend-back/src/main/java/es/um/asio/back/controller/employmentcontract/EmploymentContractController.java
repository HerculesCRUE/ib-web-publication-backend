package es.um.asio.back.controller.employmentcontract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.EmploymentContractDto;
import es.um.asio.service.filter.employmentcontract.EmploymentContractFilter;
import es.um.asio.service.proxy.employmentcontract.EmploymentContractProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(EmploymentContractController.Mappings.BASE)
public class EmploymentContractController {

	@Autowired
	private EmploymentContractProxy proxy;
	
	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;


	@GetMapping(EmploymentContractController.Mappings.SEARCH)
	public Page<EmploymentContractDto> searchProyects(final EmploymentContractFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/employmentContract";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";
	}
}