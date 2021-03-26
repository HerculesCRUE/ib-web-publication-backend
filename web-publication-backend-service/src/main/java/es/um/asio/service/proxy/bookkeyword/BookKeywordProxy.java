package es.um.asio.service.proxy.bookkeyword;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.BookKeywordDto;
import es.um.asio.service.filter.bookkeyword.BookKeywordFilter;

public interface BookKeywordProxy {

	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<BookKeywordDto>
	 */
	Page<BookKeywordDto> findPaginated(BookKeywordFilter filter, Pageable pageable);
}
