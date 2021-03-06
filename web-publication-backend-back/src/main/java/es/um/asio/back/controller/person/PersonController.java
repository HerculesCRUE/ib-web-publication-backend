package es.um.asio.back.controller.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.PersonDetailDto;
import es.um.asio.service.dto.PersonDto;
import es.um.asio.service.dto.graphic.GraphicsDto;
import es.um.asio.service.filter.person.PersonFilter;
import es.um.asio.service.proxy.person.PersonProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


/**
 * Person controller.
 */
@RestController
@RequestMapping(PersonController.Mappings.BASE)
public class PersonController {

	/** The proxy. */
	@Autowired
	private PersonProxy proxy;

	/** The fuseki trellis url. */
	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;

	/**
	 * Search persons.
	 *
	 * @param filter the filter
	 * @param pageable the pageable
	 * @return the page
	 */
	@GetMapping(PersonController.Mappings.SEARCH)
	public Page<PersonDto> searchPersons(final PersonFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}

	/**
	 * Area.
	 *
	 * @return the list
	 */
	@GetMapping(PersonController.Mappings.AREA)
	public List<GraphicsDto> area() {
		return this.proxy.getArea();
	}

	/**
	 * Find person.
	 *
	 * @param id the id
	 * @return the person detail dto
	 */
	@GetMapping(PersonController.Mappings.GET)
	public PersonDetailDto findPerson(@PathVariable("id") final String id) {
		return this.proxy.find(id);
	}
	
	
	/**
	 * Find person by project.
	 * People involved in the project 
	 *
	 * @param filter the filter
	 * @param pageable the pageable
	 * @return the page
	 */
	@GetMapping(PersonController.Mappings.BY_PROJECT)
	public Page<PersonDto> findPersonByProject(final PersonFilter filter, final Pageable pageable) {
		return this.proxy.findPaginatedByProject(filter, pageable);
	}

	/**
	 * Instantiates a new mappings.
	 */
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/person";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";

		/**
		 * Mapping for get.
		 */
		protected static final String GET = "/{id}";

		/**
		 * Graphics area.
		 */
		protected static final String AREA = "/area";
		
		/**
		 * Graphics area.
		 */
		protected static final String BY_PROJECT = "/byproject";
	}
}
