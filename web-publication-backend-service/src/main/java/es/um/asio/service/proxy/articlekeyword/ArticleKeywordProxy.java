package es.um.asio.service.proxy.articlekeyword;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.ArticleKeywordDto;
import es.um.asio.service.filter.articlekeyword.ArticleKeywordFilter;

public interface ArticleKeywordProxy {

	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<ArticleDto>
	 */
	Page<ArticleKeywordDto> findPaginated(ArticleKeywordFilter filter, Pageable pageable);
}
