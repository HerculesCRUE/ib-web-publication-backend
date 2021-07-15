package es.um.asio.back.controller.dataimporter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.proxy.dataimporter.DataImporterProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * AcademicPublication controller.
 */
@RestController
@RequestMapping(DataImporterController.Mappings.BASE)
public class DataImporterController {
		
	@Autowired
	private DataImporterProxy dataImporterProxy;
	
	@GetMapping(DataImporterController.Mappings.ERRORS)
	public List<String> dataImporterError(@PathVariable("id") final String id) {		
		return dataImporterProxy.findErrors(id);
	}

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/importer";
		
		/**
		 * Controller request mapping.
		 */
		protected static final String ERRORS = "/{id}/errors";
	}
}
