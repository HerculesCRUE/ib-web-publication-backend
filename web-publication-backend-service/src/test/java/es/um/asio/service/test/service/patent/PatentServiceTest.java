package es.um.asio.service.test.service.patent;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import es.um.asio.service.filter.patent.PatentFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.service.patent.PatentService;

@RunWith(SpringRunner.class)
public class PatentServiceTest {

	@MockBean
	private PatentService service;

	PatentFilter filter;

	Pageable pageable;

	@Before
	public void beforeTest() {

		filter = new PatentFilter();

		filter.setId("1");
		filter.setLanguage("es");

		pageable = PageRequest.of(1, 5, Sort.by("ASC"));
		FusekiResponse fuseki = new FusekiResponse();
		List<FusekiResponse> contentResult = new ArrayList<>();
		// Mock
		Mockito.when(this.service.findPaginated(filter, pageable)).thenAnswer(invocation -> {

			String head = "\"head\": {\r\n"
					+ "    \"vars\": [ \"x\" , \"name\" , \"ini\" , \"fin\" , \"id\" , \"tipo\" ]\r\n" + "  }";
			
			Map<String, List<Map<String, Map<String, String>>>> resultMap = new HashMap<>();
			
			resultMap.put("bindings", new ArrayList<Map<String, Map<String, String>>>());
			
			Map<String, Map<String, String>> bindings = new HashMap<>();
			bindings.put("id", this.propertyMap("literal", "es", "52"));
			bindings.put("title", this.propertyMap("literal", "es", "title"));
			bindings.put("dateIssued", this.propertyMap("literal", "es", ""));
			bindings.put("doi", this.propertyMap("literal", "es", ""));
			bindings.put("endDate", this.propertyMap("literal", "es", ""));
			bindings.put("endPage", this.propertyMap("literal", "es", ""));
			bindings.put("keyword", this.propertyMap("literal", "es", ""));
			bindings.put("mode", this.propertyMap("literal", "es", ""));
			bindings.put("startDate", this.propertyMap("literal", "es", ""));
			bindings.put("startPage", this.propertyMap("literal", "es", ""));
			
			resultMap.get("bindings").add(bindings);

			fuseki.setHead(head);
			fuseki.setResults(resultMap);
			contentResult.add(fuseki);
			Page<FusekiResponse> page = new PageImpl<>(contentResult, pageable, contentResult.size());
			
			return page;
		});
		
		Mockito.when(this.service.retrieveEntity()).thenAnswer(invocation -> {
			return new Entity("Patent", "dateIssued", "doi", "endDate", "endPage", "id", "keyword", "mode", "startDate", "startPage", "title");
		});
	}

	@Test
	public void testEntity() {
		Entity entity = new Entity("Patent", "dateIssued", "doi", "endDate", "endPage", "id", "keyword", "mode", "startDate", "startPage", "title");
		Entity entityFromService = this.service.retrieveEntity();
		
		assertThat(entityFromService).isEqualTo(entity);
	}

	@Test
	public void testfindPatent() {
		System.out.println(filter.toString());
		Page<FusekiResponse> page = this.service.findPaginated(filter, pageable);
		
		assertNotNull(page);
		assertEquals(true, page.getContent().get(0).getResults().toString().contains("52"));
	}
	
	private Map<String, String> propertyMap(String type, String lang, String value) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", type);
		map.put("xml:lang", lang);
		map.put("value", value);
		
		return map; 
	}

}
