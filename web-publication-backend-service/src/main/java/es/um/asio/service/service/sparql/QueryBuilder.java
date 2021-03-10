package es.um.asio.service.service.sparql;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import es.um.asio.service.model.Entity;

public interface QueryBuilder {

	Map<String, String> queryChunks(Entity entity, Pageable pageable);
	
	Map<String, String> queryChunks(Entity entity);
}
