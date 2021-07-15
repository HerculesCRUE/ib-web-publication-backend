package es.um.asio.back.test.proxy;

import static org.junit.Assert.assertNotNull;

import es.um.asio.back.test.mock.MockMailSender;
import es.um.asio.service.mapper.decorator.ArticleMapperDecorator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import es.um.asio.service.proxy.patent.PatentProxy;
import es.um.asio.service.service.patent.PatentService;

@RunWith(SpringRunner.class)
@WebMvcTest(PatentProxy.class)
@ActiveProfiles("unit-test")
public class PatentProxyTest {

	/**
	 * Proxy Project
	 */
	@Autowired
	private PatentProxy proxy;

	@MockBean
	private PatentService service;

	@TestConfiguration
	static class PatentProxyTestConfiguration {

		@Bean
		public JavaMailSender mailSender() {
			final JavaMailSenderImpl sender;
			sender = new MockMailSender();
			return sender;
		}
	}

	@Test
	public void test_Infraestructure() {
		assertNotNull(proxy);
	}
}
