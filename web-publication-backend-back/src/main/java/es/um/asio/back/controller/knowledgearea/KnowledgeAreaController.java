package es.um.asio.back.controller.knowledgearea;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.KnowledgeAreaDto;
import es.um.asio.service.filter.knowledgearea.KnowledgeAreaFilter;
import es.um.asio.service.proxy.knowledgearea.KnowledgeAreaProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(KnowledgeAreaController.Mappings.BASE)
public class KnowledgeAreaController {

	@Autowired
	private KnowledgeAreaProxy proxy;
	
	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;


	@GetMapping(KnowledgeAreaController.Mappings.SEARCH)
	public Page<KnowledgeAreaDto> searchProyects(final KnowledgeAreaFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}
	
	@GetMapping(KnowledgeAreaController.Mappings.ALL)
	public List<KnowledgeAreaDto> findAll() {
		return this.proxy.findAll();
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/knowledgearea";

		/**
		 * Mapping for all.
		 */
		protected static final String ALL = "/all";
		
		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";
	}
}
