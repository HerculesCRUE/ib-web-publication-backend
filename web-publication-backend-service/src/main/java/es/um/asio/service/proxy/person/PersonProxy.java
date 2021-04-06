package es.um.asio.service.proxy.person;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.PersonDetailDto;
import es.um.asio.service.dto.PersonDto;
import es.um.asio.service.filter.person.PersonFilter;

public interface PersonProxy {

	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<PersonDto>
	 */
	Page<PersonDto> findPaginated(PersonFilter filter, Pageable pageable);

	/**
	 * Obtiene una persona
	 * 
	 * @param id
	 * @return PersonDetailDto
	 */
	PersonDetailDto find(String id);

	String getArea();
}
