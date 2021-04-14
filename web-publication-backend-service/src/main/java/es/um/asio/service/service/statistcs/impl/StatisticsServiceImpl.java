package es.um.asio.service.service.statistcs.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.um.asio.service.model.Entity;
import es.um.asio.service.model.SimpleQuery;
import es.um.asio.service.service.sparql.SparqlExecQuery;
import es.um.asio.service.service.statistcs.StatisticsService;

@Service
public class StatisticsServiceImpl implements StatisticsService {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(StatisticsServiceImpl.class);

	@Autowired
	private SparqlExecQuery serviceSPARQL;

	@Override
	public List<Object> articlesByPublishedIn() {
		logger.info("Start sparql articlesByPublishedIn");
		SimpleQuery query = new SimpleQuery(this.retrieveArticleGraphicEntity(), "");

		return serviceSPARQL.runCount(query);
	}

	private Entity retrieveArticleGraphicEntity() {
		Entity entity = new Entity("Article", "publishedIn");
		List<String> groups = new ArrayList<>();
		groups.add("publishedIn");
		entity.setGroup(groups);
		return entity;
	}

	@Override
	public List<Object> projectByClassification() {
		logger.info("Start sparql projectByClassification");
		SimpleQuery query = new SimpleQuery(this.projectByClassificationGraphicEntity(), "");
		return serviceSPARQL.runCount(query);
	}

	private Entity projectByClassificationGraphicEntity() {
		Entity entity = new Entity("Project", "projectClassification");
		List<String> groups = new ArrayList<>();
		groups.add("projectClassification");
		entity.setGroup(groups);
		return entity;
	}

}
