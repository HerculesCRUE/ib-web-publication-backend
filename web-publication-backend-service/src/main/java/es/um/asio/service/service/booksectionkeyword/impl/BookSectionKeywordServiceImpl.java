package es.um.asio.service.service.booksectionkeyword.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.filter.booksectionkeyword.BookSectionKeywordFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.model.PageableQuery;
import es.um.asio.service.service.booksection.impl.BookSectionServiceImpl;
import es.um.asio.service.service.booksectionkeyword.BookSectionKeywordService;
import es.um.asio.service.service.impl.FusekiService;
import es.um.asio.service.service.sparql.SparqlExecQuery;

@Service
public class BookSectionKeywordServiceImpl extends FusekiService<BookSectionKeywordFilter> implements BookSectionKeywordService {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(BookSectionServiceImpl.class);

	@Autowired
	private SparqlExecQuery serviceSPARQL;
	
	@Override
	public Page<FusekiResponse> findPaginated(BookSectionKeywordFilter filter, Pageable pageable) {
		logger.info("Searching BookSection keywords with filter: {} page: {}", filter, pageable);

		PageableQuery pageableQuery = new PageableQuery(this.retrieveEntity(), filtersChunk(filter), pageable);

		return serviceSPARQL.run(pageableQuery);
	}

	@Override
	public String filtersChunk(BookSectionKeywordFilter filter) {
		StringBuilder strBuilder = new StringBuilder();
		
		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getKeyword())) {
				strBuilder.append("FILTER (LANG(?keyword) = \"");
				strBuilder.append(filter.getLanguage().substring(1));
				strBuilder.append("\") . ");
				strBuilder.append("FILTER ( regex(?keyword, \"");
				strBuilder.append(filter.getKeyword());
				strBuilder.append("\", \"i\")) . ");
			}
		}
		
		return strBuilder.toString();
	}

	@Override
	public Entity retrieveEntity() {
		return new Entity("Book-Section-Keyword", "keyword");
	}
}
