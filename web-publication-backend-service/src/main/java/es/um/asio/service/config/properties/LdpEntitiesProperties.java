package es.um.asio.service.config.properties;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties("app.ldp")
@Getter
@Setter
public class LdpEntitiesProperties {
	
	private String uriNamespace;
	
	private String[] validProperties;
	
	private String[] invalidEntities;
	
	private String[] filteredDetailsProperties;
	
	private List<LdpEntityProperties> entities;
	
	public boolean isValidEntity(String uri) {
		return filterInvalidEntities(uri::contains);				
	}
	
	public boolean isValidCategory(String uri) {
		return filterInvalidEntities(uri::endsWith);		
	}
	
	private boolean filterInvalidEntities (Predicate<String> filter) {
		return Stream.of(invalidEntities)				
				.map(type -> type.split(","))
				.flatMap(Stream::of)
				.map(String::trim)
				.filter(filter)
				.findFirst()
				.orElse(null) == null;		
	}
}
