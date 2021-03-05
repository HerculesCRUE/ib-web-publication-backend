package es.um.asio.service.test.proxy;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import es.um.asio.service.dto.PatentDto;
import es.um.asio.service.filter.patent.PatentFilter;
import es.um.asio.service.mapper.decorator.PatentMapperDecorator;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.service.patent.PatentService;

@RunWith(SpringRunner.class)
public class PatentProxyTest {
	
	@Autowired
	private PatentMapperDecorator mapper;

	@MockBean
	private PatentService service;

	PatentFilter filter;

	Pageable pageable;

	@TestConfiguration
	static class PatentProxyTestConfiguration {
		
		@Bean
		@Qualifier("delegate")
		public PatentMapperDecorator patentMapper() {
			return new PatentMapperDecorator();
		}
	}

	@Before
	public void set_Up() {
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
	}

	@Test
	public void proxyTest() {
		Page<PatentDto> page = this.mapper.convertPageFusekiResponseToDto(this.service.findPaginated(filter, pageable));

		assertNotNull(page);
	}
	
	private Map<String, String> propertyMap(String type, String lang, String value) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", type);
		map.put("xml:lang", lang);
		map.put("value", value);
		
		return map; 
	}
}
