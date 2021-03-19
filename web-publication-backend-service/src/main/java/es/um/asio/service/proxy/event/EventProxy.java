package es.um.asio.service.proxy.event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.EventDetailDto;
import es.um.asio.service.dto.EventDto;
import es.um.asio.service.filter.event.EventFilter;

public interface EventProxy {

	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<EventDto>
	 */
	Page<EventDto> findPaginated(EventFilter filter, Pageable pageable);
	
	/**
	 * Obtiene un evento
	 * 
	 * @param id
	 * @param type
	 * @return EventDto
	 */
	EventDetailDto find(String id, String type);
}
