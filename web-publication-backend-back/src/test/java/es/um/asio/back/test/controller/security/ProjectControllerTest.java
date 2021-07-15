package es.um.asio.back.test.controller.security;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import es.um.asio.back.test.mock.MockMailSender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import es.um.asio.back.controller.project.ProjectController;
import es.um.asio.service.dto.ProjectDto;
import es.um.asio.service.filter.project.ProjectFilter;
import es.um.asio.service.proxy.project.ProjectProxy;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectController.class)
@ActiveProfiles("unit-test")
public class ProjectControllerTest {

	@MockBean
	private ProjectProxy projectProxy;

	@Autowired
	private MockMvc mvc;

	@TestConfiguration
	static class PatentProxyTestConfiguration {

		@Bean
		public JavaMailSender mailSender() {
			final JavaMailSenderImpl sender;
			sender = new MockMailSender();
			return sender;
		}
	}

	@Before
	public void beforeTest() {

		ProjectFilter filter = new ProjectFilter();
		filter.setTitle("TITLE");
		filter.setLanguage("es");

		Pageable pageable = PageRequest.of(1, 5, Sort.by("ASC"));
		
		List<ProjectDto> contentResult = new ArrayList<>();
		// Mock StorageType proxy
		Mockito.when(this.projectProxy.findPaginated(filter, pageable)).thenAnswer(invocation -> {

			ProjectDto project = new ProjectDto();
			project.setAbbreviation("projectAbbreviation");
//			project.setDescription("projectDescription");
			project.setEndDate("projectEndDate");
//			project.setForeseenJustificationDate("projectForeseenJustificationDate");
			project.setId("projectId");
//			project.setKeyword("projectKeyword");
//			project.setModality("projectModality");
//			project.setNeedsEthicalValidation("projectNeedsEthicalValidation");
			project.setStartDate("projectStartDate");
			project.setStatus("projectStatus");
			project.setTitle("projectTitle");

			contentResult.add(project);
			Page<ProjectDto> page = new PageImpl<>(contentResult, pageable, contentResult.size());
			return page;
		});
	}

	@Test
	public void test_Infraestructure() {
		assertNotNull(mvc);
	}

	@Test
	public void process() {

		try {
			this.mvc.perform(get("/project/search").content("").contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
		} catch (Exception e) {
			System.out.print("ERROR: " + e.getCause());
		}

	}

}
