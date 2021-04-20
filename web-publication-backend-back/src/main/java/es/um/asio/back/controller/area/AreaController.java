package es.um.asio.back.controller.area;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.graphic.GraphicsDto;
import es.um.asio.service.filter.area.AreaFilter;
import es.um.asio.service.proxy.area.AreaProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(AreaController.Mappings.BASE)
public class AreaController {

	@Autowired
	private AreaProxy proxy;

	@GetMapping(AreaController.Mappings.RESEARCHGROUPBYAREA)
	public List<GraphicsDto> getAreaByresearchGroup() {
		return this.proxy.getAreaByresearchGroup();
	}

	@GetMapping(AreaController.Mappings.PROJECT_AREA)
	public String getAreasWithMoreProjects(final AreaFilter filter) {
		return this.proxy.getAreasWithMoreProjects(filter);
	}

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {

		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/areas";

		/**
		 * Mapping for search.
		 */
		protected static final String RESEARCHGROUPBYAREA = "/researchGroupByArea";

		/**
		 * Mapping for search.
		 */
		protected static final String PROJECT_AREA = "/projectArea";
	}
}
