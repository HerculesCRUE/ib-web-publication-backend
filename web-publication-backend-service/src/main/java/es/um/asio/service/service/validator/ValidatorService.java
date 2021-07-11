package es.um.asio.service.service.validator;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.domain.Validator;

public interface ValidatorService {

	Page<Validator> findPaginated(Pageable pageable);
	
	Validator save(Validator validator);
	
	void delete(String id);
	
	Validator update(Validator validator);
}
