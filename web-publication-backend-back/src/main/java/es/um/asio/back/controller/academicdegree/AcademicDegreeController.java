package es.um.asio.back.controller.academicdegree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.AcademicDegreeDto;
import es.um.asio.service.filter.academicdegree.AcademicDegreeFilter;
import es.um.asio.service.proxy.academicdegree.AcademicDegreeProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(AcademicDegreeController.Mappings.BASE)
public class AcademicDegreeController {

	@Autowired
	private AcademicDegreeProxy proxy;
	
	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;


	@GetMapping(AcademicDegreeController.Mappings.SEARCH)
	public Page<AcademicDegreeDto> searchProyects(final AcademicDegreeFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/academicdegree";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";
	}
}
