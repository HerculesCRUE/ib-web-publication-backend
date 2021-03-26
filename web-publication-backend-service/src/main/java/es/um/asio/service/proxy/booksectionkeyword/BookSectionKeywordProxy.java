package es.um.asio.service.proxy.booksectionkeyword;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.BookSectionKeywordDto;
import es.um.asio.service.filter.booksectionkeyword.BookSectionKeywordFilter;

public interface BookSectionKeywordProxy {

	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<BookSectionKeywordDto>
	 */
	Page<BookSectionKeywordDto> findPaginated(BookSectionKeywordFilter filter, Pageable pageable);
}
