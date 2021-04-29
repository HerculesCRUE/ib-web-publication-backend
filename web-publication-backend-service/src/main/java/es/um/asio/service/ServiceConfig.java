package es.um.asio.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import es.um.asio.service.repository.RepositoryConfig;

/**
 * Service Spring configuration.
 */
@Configuration
@ComponentScan
@Import({ RepositoryConfig.class })
public class ServiceConfig {

}
