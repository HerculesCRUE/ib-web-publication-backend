package es.um.asio.back.controller.dataimporter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.DataImporterDto;
import es.um.asio.service.dto.DataImporterErrorDto;
import es.um.asio.service.filter.dataimporter.DataImporterFilter;
import es.um.asio.service.util.PageImplHelper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * AcademicPublication controller.
 */
@RestController
@RequestMapping(DataImporterController.Mappings.BASE)
public class DataImporterController {
	
	private final Logger logger = LoggerFactory.getLogger(DataImporterController.class);
	
	@GetMapping(DataImporterController.Mappings.SEARCH)
	public Page<DataImporterDto> searchDataImporters(final DataImporterFilter filter, final Pageable pageable) {
		
		DataImporterDto row1 = new DataImporterDto();
		DataImporterDto row2 = new DataImporterDto();
		
		row1.setId("1");
		row1.setDate(new Date());
		row1.setUser("user1");
		row1.setType("Dataset");
		row1.setCron("* * 15 *");
		
		List<String> listErrors1 = new ArrayList<>();
		listErrors1.add("Error saving object Articulo, ID=154789");
		listErrors1.add("Error creating RDF Proyecto, ID=589");
		listErrors1.add("Error saving object GrupoInvestigador, ID=1549");
		listErrors1.add("Error saving object GrupoInvestigador, ID=188789");
				
		DataImporterErrorDto errors1 = new DataImporterErrorDto();
		errors1.setErrors(listErrors1);
		row1.setImporterErrors(errors1);
		
		row2.setId("2");
		row2.setDate(new Date());
		row2.setUser("user2");
		row2.setType("CVN");
		row2.setCron("0 15 15 *");
		
		List<String> listErrors2 = new ArrayList<>();
		listErrors2.add("Error saving object GrupoInvestigador, ID=154789");
		listErrors2.add("Error creating RDF Proyecto, ID=589");
		listErrors2.add("Error creating RDF Profesor, ID=74189");
				
		DataImporterErrorDto errors2 = new DataImporterErrorDto();
		errors2.setErrors(listErrors2);
		row2.setImporterErrors(errors2);
		
				
		List<DataImporterDto> content = new ArrayList<>();
		content.add(row1);
		content.add(row2);
		
		
		PageImplHelper<DataImporterDto> result = new PageImplHelper<DataImporterDto>(content, pageable, content.size());
				
		return result;
	}

	@PostMapping(DataImporterController.Mappings.IMPORT)
	public DataImporterDto importation(@RequestBody final DataImporterDto importation) {
		logger.info("Launching the importation with params {}", importation);
		return importation;
	}

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/importer";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";
		
		/**
         * Mapping for get.
         */
        protected static final String GET = "/{id}";
        
        /**
         * Mapping for import.
         */
        protected static final String IMPORT = "/schedule";
	}
}
