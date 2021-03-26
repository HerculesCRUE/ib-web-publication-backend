package es.um.asio.service.proxy.conferencekeyword;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.ConferenceKeywordDto;
import es.um.asio.service.filter.conferencekeyword.ConferenceKeywordFilter;

public interface ConferenceKeywordProxy {

	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<ConferenceKeywordDto>
	 */
	Page<ConferenceKeywordDto> findPaginated(ConferenceKeywordFilter filter, Pageable pageable);
}
