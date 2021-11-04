package es.um.asio.service.service.ldp;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.LdpEntityCountDto;
import es.um.asio.service.dto.LdpEntityDetailsDto;
import es.um.asio.service.dto.LdpEntityRelatedDto;
import es.um.asio.service.dto.LdpSearchResultDto;

public interface LdpService {

	Page<LdpEntityCountDto> entityCount(Pageable pageable); 
	
	Page<LdpSearchResultDto> findByTitleOrName(final String title, final Pageable pageable);
	
	Page<LdpSearchResultDto> findByCategory(final String category, final Pageable pageable);
	
	LdpEntityDetailsDto findDetails(final String uri);
	
	Page<LdpEntityRelatedDto> findRelated(final String uri, final Pageable pageable, final String type);
	
	List<String> findRelatedCategories(final String uri, final String type);
}
