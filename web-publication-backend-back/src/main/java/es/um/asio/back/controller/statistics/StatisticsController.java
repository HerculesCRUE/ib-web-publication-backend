package es.um.asio.back.controller.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.proxy.statistics.StatisticsProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(StatisticsController.Mappings.BASE)
public class StatisticsController {

	@Autowired
	private StatisticsProxy proxy;

	@GetMapping(StatisticsController.Mappings.TOP_PUBLICATIONS)
	public String topPublications() {
		return this.proxy.topPublications();
	}

	@GetMapping(StatisticsController.Mappings.TOP_PATENTS)
	public String topPatents() {
		return this.proxy.topPatents();
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
		protected static final String TOP_PATENTS = "/topPatents";

		/**
		 * Graphics investigation actions.
		 */
		protected static final String TOP_PUBLICATIONS = "/topPublications";
	}

}
