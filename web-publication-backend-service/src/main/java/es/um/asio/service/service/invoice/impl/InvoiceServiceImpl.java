package es.um.asio.service.service.invoice.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.filter.invoice.InvoiceFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.model.PageableQuery;
import es.um.asio.service.service.article.impl.ArticleServiceImpl;
import es.um.asio.service.service.impl.FusekiService;
import es.um.asio.service.service.invoice.InvoiceService;
import es.um.asio.service.service.sparql.SparqlExecQuery;

@Service
public class InvoiceServiceImpl extends FusekiService<InvoiceFilter> implements InvoiceService {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

	@Autowired
	private SparqlExecQuery serviceSPARQL;

	@Override
	public Page<FusekiResponse> findPaginated(InvoiceFilter filter, Pageable pageable) {
		logger.info("Searching invoices with filter: {} page: {}", filter, pageable);

		PageableQuery pageableQuery = new PageableQuery(this.retrieveEntity(), filtersChunk(filter), pageable);

		return serviceSPARQL.run(pageableQuery);
	}

	@Override
	public String filtersChunk(InvoiceFilter filter) {
		StringBuilder strBuilder = new StringBuilder();

		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getDateTime())) {
				strBuilder.append("FILTER (?dateTime = \"");
				strBuilder.append(filter.getDateTime());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getDoi())) {
				strBuilder.append("FILTER (?doi = \"");
				strBuilder.append(filter.getDoi());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getEndPage())) {
				strBuilder.append("FILTER (?endPage = \"");
				strBuilder.append(filter.getEndPage());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getId())) {
				strBuilder.append("FILTER (regex(?id, \"^");
				strBuilder.append(filter.getId());
				strBuilder.append("$\")) . ");
			}

			if (StringUtils.isNotBlank(filter.getKeyword())) {
				strBuilder.append("FILTER (?keyword = \"");
				strBuilder.append(filter.getKeyword());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getPublishedIn())) {
				strBuilder.append("FILTER (?publishedIn = \"");
				strBuilder.append(filter.getPublishedIn());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getStartPage())) {
				strBuilder.append("FILTER (?startPage = \"");
				strBuilder.append(filter.getStartPage());
				strBuilder.append("\"");
				strBuilder.append(filter.getLanguage());
				strBuilder.append(") . ");
			}

			if (StringUtils.isNotBlank(filter.getSummary())) {
				strBuilder.append("FILTER (LANG(?summary) = \"");
				strBuilder.append(filter.getLanguage().substring(1));
				strBuilder.append("\") . ");
				strBuilder.append("FILTER ( regex(?summary, \"");
				strBuilder.append(filter.getSummary());
				strBuilder.append("\", \"i\")) . ");
			}

			if (StringUtils.isNotBlank(filter.getTitle())) {
				strBuilder.append("FILTER (LANG(?title) = \"");
				strBuilder.append(filter.getLanguage().substring(1));
				strBuilder.append("\") . ");
				strBuilder.append("FILTER ( regex(?title, \"");
				strBuilder.append(filter.getTitle());
				strBuilder.append("\", \"i\")) . ");
			}
		}

		return strBuilder.toString();
	}

	@Override
	public Entity retrieveEntity() {
		return new Entity("Invoice", "dateTime", "doi", "endPage", "id", "keyword", "publishedIn", "startPage",
				"title");
	}

}
