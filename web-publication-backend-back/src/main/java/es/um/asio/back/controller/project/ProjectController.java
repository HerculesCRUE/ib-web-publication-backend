package es.um.asio.back.controller.project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.service.dto.PersonDto;
import es.um.asio.service.dto.PersonParticipantDto;
import es.um.asio.service.dto.ProjectDetailDto;
import es.um.asio.service.dto.ProjectDto;
import es.um.asio.service.dto.graphic.GraphicsDto;
import es.um.asio.service.filter.person.PersonFilter;
import es.um.asio.service.filter.project.ProjectFilter;
import es.um.asio.service.proxy.project.ProjectProxy;
import es.um.asio.service.util.PageImplHelper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Project controller.
 */
@RestController
@RequestMapping(ProjectController.Mappings.BASE)
public class ProjectController {

	@Autowired
	private ProjectProxy proxy;

	@Value("${app.fusekitrellis.url}")
	private String fusekiTrellisUrl;

	@GetMapping(ProjectController.Mappings.SEARCH)
	public Page<ProjectDto> searchProyects(final ProjectFilter filter, final Pageable pageable) {
		return this.proxy.findPaginated(filter, pageable);
	}

	@GetMapping(ProjectController.Mappings.GET)
	public ProjectDetailDto findProject(@PathVariable("id") final String id) {
		return this.proxy.find(id);
	}

	@GetMapping(ProjectController.Mappings.BYMODALITY)
	public List<GraphicsDto> getbyModality() {
		return this.proxy.getbyModality();
	}
	
	@GetMapping(ProjectController.Mappings.GET + ProjectController.Mappings.PARTICIPANTS)
	public Page<PersonDto> getParticipants(@PathVariable("id") final String id, final PersonFilter filter, final Pageable pageable) {
		Page<PersonParticipantDto> page = this.proxy.getParticipants(id, filter, pageable);
		
		List<PersonDto> persons = new ArrayList<>();
		
		for (PersonParticipantDto person : page.getContent()) {
			PersonDto p = new PersonDto();
			
			p.setBirthDate(person.getRelatesBirthDate());
			p.setDescription(person.getRelatesDescription());
			p.setFirstName(person.getRelatesFirstName());
			p.setGender(person.getRelatesGender());
			p.setHasContactInfo(person.getRelatesHasContactInfo());
			p.setHomepage(person.getRelatesHomepage());
			p.setId(person.getRelatesId());
			p.setImage(person.getRelatesImage());
			p.setName(person.getRelatesName());
			p.setNickname(person.getRelatesNickname());
			p.setPersonalMaibox(person.getRelatesPersonalMaibox());
			p.setResearchLine(person.getRelatesResearchLine());
			p.setSubjectArea(person.getRelatesSubjectArea());
			p.setSurname(person.getRelatesSurname());
			p.setTaxId(person.getRelatesTaxId());
			
			persons.add(p);
		}
		
		return new PageImplHelper<PersonDto>(persons, page.getPageable(), page.getTotalElements());
	}

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/project";

		/**
		 * Mapping for search.
		 */
		protected static final String SEARCH = "/search";

		/**
		 * Mapping for get.
		 */
		protected static final String GET = "/{id}";

		/**
		 * Graphics investigation actions.
		 */
		protected static final String BYMODALITY = "/byModality";
		
		/**
		 * Mapping for participants.
		 */
		protected static final String PARTICIPANTS = "/participants";
	}
}
