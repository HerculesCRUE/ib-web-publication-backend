package es.um.asio.back.test.controller.security;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import es.um.asio.back.controller.patent.PatentController;
import es.um.asio.service.dto.PatentDto;
import es.um.asio.service.filter.patent.PatentFilter;
import es.um.asio.service.proxy.patent.PatentProxy;

@RunWith(SpringRunner.class)
@WebMvcTest(PatentController.class)
@ActiveProfiles("unit-test")
public class PatentControllerTest {

	@MockBean
	private PatentProxy patentProxy;

	@Autowired
	private MockMvc mvc;

	@Before
	public void beforeTest() {

		PatentFilter filter = new PatentFilter();
		filter.setTitle("TITLE");
		filter.setLanguage("es");

		Pageable pageable = PageRequest.of(1, 5, Sort.by("ASC"));

		List<PatentDto> contentResult = new ArrayList<>();
		// Mock StorageType proxy
		Mockito.when(this.patentProxy.findPaginated(filter, pageable)).thenAnswer(invocation -> {

			PatentDto patent = new PatentDto();
			patent.setId("patentId");
			patent.setDateIssued("patentDateIssued");
			patent.setDoi("patentDoi");
			patent.setEndDate("patentEndDate");
			patent.setPageEnd("patentPageEnd");
			patent.setKeyword("patentKeyword");
			patent.setMode("patentMode");
			patent.setStartDate("patentStartDate");
			patent.setStartPage("patentStartPage");
			patent.setTitle("patentTitle");
			
			contentResult.add(patent);
			Page<PatentDto> page = new PageImpl<>(contentResult, pageable, contentResult.size());
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
			this.mvc.perform(get("/patent/search").content("").contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
		} catch (Exception e) {
			System.out.print("ERROR: " + e.getCause());
		}

	}
}
