package es.um.asio.service.proxy.validator;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.ValidatorDto;

public interface ValidatorProxy {

	Page<ValidatorDto> findPaginated(Pageable pageable);

	ValidatorDto save(ValidatorDto validatorDto);

	ValidatorDto update(ValidatorDto validatorDto);

	void delete(String id);
}
