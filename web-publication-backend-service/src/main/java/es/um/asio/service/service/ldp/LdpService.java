package es.um.asio.service.service.ldp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.LdpEntityCountDto;

public interface LdpService {

	Page<LdpEntityCountDto> entityCount(Pageable pageable); 
}
