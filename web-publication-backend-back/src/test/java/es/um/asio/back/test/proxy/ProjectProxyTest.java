package es.um.asio.back.test.proxy;

import static org.junit.Assert.assertNotNull;

import es.um.asio.back.test.mock.MockMailSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import es.um.asio.service.proxy.project.ProjectProxy;
import es.um.asio.service.service.project.ProjectService;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectProxy.class)
@ActiveProfiles("unit-test")
public class ProjectProxyTest {

	/**
	 * Proxy Project
	 */
	@Autowired
	private ProjectProxy proxy;

	@MockBean
	private ProjectService service;

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
