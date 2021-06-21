package es.um.asio.back.controller.dataimporter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.DataImporterDto;
import es.um.asio.service.filter.dataimporter.DataImporterFilter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * AcademicPublication controller.
 */
@RestController
@RequestMapping(DataImporterController.Mappings.BASE)
public class DataImporterController {
	
	
	@GetMapping(DataImporterController.Mappings.SEARCH)
	public Page<DataImporterDto> searchDataImporters(final DataImporterFilter filter, final Pageable pageable) {
		// return this.proxy.findPaginated(filter, pageable);
		return null;
	}
	

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/dataimporter";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";
		
		/**
         * Mapping for get.
         */
        protected static final String GET = "/{id}";
	}
}
