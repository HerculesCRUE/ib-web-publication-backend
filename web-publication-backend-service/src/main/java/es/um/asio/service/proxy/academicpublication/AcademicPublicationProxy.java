package es.um.asio.service.proxy.academicpublication;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.AcademicPublicationDetailDto;
import es.um.asio.service.dto.AcademicPublicationDto;
import es.um.asio.service.dto.DocumentDetailDto;
import es.um.asio.service.filter.academicpublication.AcademicPublicationFilter;

public interface AcademicPublicationProxy {

	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<AcademicPublicationDto>
	 */
	Page<AcademicPublicationDto> findPaginated(AcademicPublicationFilter filter, Pageable pageable);
	
	/**
	 * Obtiene una publicacion académica
	 * 
	 * @param id
	 * @param type
	 * @return DocumentDto
	 */
	AcademicPublicationDetailDto find(String id, String type);
}
