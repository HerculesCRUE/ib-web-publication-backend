package es.um.asio.service.proxy.gorupkeyword;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.GroupKeywordDto;
import es.um.asio.service.filter.groupkeyword.GroupKeywordFilter;

public interface GroupKeywordProxy {

	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<GroupKeywordDto>
	 */
	Page<GroupKeywordDto> findPaginated(GroupKeywordFilter filter, Pageable pageable);
}
