package es.um.asio.service.proxy.knowledgearea;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.KnowledgeAreaDto;
import es.um.asio.service.filter.knowledgearea.KnowledgeAreaFilter;

public interface KnowledgeAreaProxy {

	Page<KnowledgeAreaDto> findPaginated(KnowledgeAreaFilter filter, Pageable pageable);
	
	List<KnowledgeAreaDto> findAll();
}
