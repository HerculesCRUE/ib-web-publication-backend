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

import es.um.asio.service.dto.InvoiceDto;
import es.um.asio.service.filter.invoice.InvoiceFilter;
import es.um.asio.service.mapper.decorator.InvoiceMapperDecorator;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.service.invoice.InvoiceService;

@RunWith(SpringRunner.class)
public class InvoiceProxyTest {
	
	@Autowired
	private InvoiceMapperDecorator mapper;

	@MockBean
	private InvoiceService service;

	InvoiceFilter filter;

	Pageable pageable;

	@TestConfiguration
	static class InvoiceProxyTestConfiguration {
		
		@Bean
		@Qualifier("delegate")
		public InvoiceMapperDecorator invoiceMapper() {
			return new InvoiceMapperDecorator();
		}
	}

	@Before
	public void setUp() {
		filter = new InvoiceFilter();

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
			bindings.put("dateTime", this.propertyMap("literal", "es", ""));
			bindings.put("doi", this.propertyMap("literal", "es", ""));
			bindings.put("endPage", this.propertyMap("literal", "es", ""));
			bindings.put("keyword", this.propertyMap("literal", "es", ""));
			bindings.put("publishedIn", this.propertyMap("literal", "es", ""));
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
		Page<InvoiceDto> page = this.mapper.convertPageFusekiResponseToDto(this.service.findPaginated(filter, pageable));

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
