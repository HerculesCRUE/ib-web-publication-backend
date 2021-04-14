package es.um.asio.back.controller.statistics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.graphic.GraphicsDto;
import es.um.asio.service.proxy.statistics.StatisticsProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(StatisticsController.Mappings.BASE)
public class StatisticsController {

	@Autowired
	private StatisticsProxy proxy;

	@GetMapping(StatisticsController.Mappings.TOP_ARTICLES_PUBLISHEDIN)
	public List<GraphicsDto> topPublications() {
		return this.proxy.articlesByPublishedIn();
	}

	@GetMapping(StatisticsController.Mappings.TOP_PROJECT_BY_CLASSIFICATION)
	public List<GraphicsDto> projectByClassification() {
		return this.proxy.projectByClassification();
	}

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/statistics";

		/**
		 * Graphics investigation actions.
		 */
		protected static final String TOP_PROJECT_BY_CLASSIFICATION = "/projectByClassification";

		/**
		 * Graphics investigation actions.
		 */
		protected static final String TOP_ARTICLES_PUBLISHEDIN = "/articlesByPublishedIn";
	}

}
