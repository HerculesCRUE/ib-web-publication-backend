package es.um.asio.service.service.curriculumvitae.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.filter.curriculumvitae.CurriculumVitaeFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.model.PageableQuery;
import es.um.asio.service.service.curriculumvitae.CurriculumVitaeService;
import es.um.asio.service.service.impl.FusekiService;
import es.um.asio.service.service.sparql.SparqlExecQuery;

@Service
public class CurriculumVitaeServiceImpl extends FusekiService<CurriculumVitaeFilter> implements CurriculumVitaeService {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(CurriculumVitaeServiceImpl.class);

	@Autowired
	private SparqlExecQuery serviceSPARQL;

	@Override
	public Page<FusekiResponse> findPaginated(CurriculumVitaeFilter filter, Pageable pageable) {
		logger.info("Searching CurriculumVitaes with filter: {} page: {}", filter, pageable);

		PageableQuery pageableQuery = new PageableQuery(this.retrieveEntity(), filtersChunk(filter), pageable);

		return serviceSPARQL.run(pageableQuery);
	}

	@Override
	public String filtersChunk(CurriculumVitaeFilter filter) {
		StringBuilder strBuilder = new StringBuilder();

		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getId())) {
				strBuilder.append("FILTER (regex(?id, \"^");
				strBuilder.append(filter.getId());
				strBuilder.append("$\")) . ");
			}
		}

		return strBuilder.toString();
	}

	@Override
	public Entity retrieveEntity() {
		return new Entity("CurriculumVitae", "correspondingAuthor", "id");
	}
}
