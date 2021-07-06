package es.um.asio.back.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.ValidatorDto;
import es.um.asio.service.proxy.validator.ValidatorProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(ValidatorController.Mappings.BASE)
public class ValidatorController {
	
	@Autowired
	private ValidatorProxy validatorProxy;

	
	@GetMapping(ValidatorController.Mappings.SEARCH)
	public Page<ValidatorDto> searchQuerys(final Pageable pageable) {
		return this.validatorProxy.findPaginated(pageable);
	}

	@PostMapping
	public ValidatorDto saveQuery(@RequestBody final ValidatorDto validatorDto) {
		return this.validatorProxy.save(validatorDto);
	}

	@PutMapping
	public ValidatorDto updateQuery(@RequestBody final ValidatorDto sparqlQuery) {
		return this.validatorProxy.update(sparqlQuery);
	}

	@DeleteMapping("/{id}")
	public void deleteQuery(@PathVariable("id") final String id) {
		this.validatorProxy.delete(id);
	}
	
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {

		protected static final String SEARCH = "/search";

		protected static final String SAVE = "/save";

		protected static final String DELETE = "/delete/";

		protected static final String UPDATE = "/update";

		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/validator";
	}
}
