package es.um.asio.service.service.validator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.domain.Validator;
import es.um.asio.service.repository.ValidatorRepository;
import es.um.asio.service.service.validator.ValidatorService;

@Service
public class ValidatorServiceImpl implements ValidatorService{

	@Autowired
	private ValidatorRepository validatorRepository;

	@Override
	public Page<Validator> findPaginated(Pageable pageable) {
		
		return validatorRepository.findAll(pageable);
	}

	@Override
	public Validator save(Validator validator) {
		Validator existingValidator = validatorRepository.findFirstByEntity(validator.getEntity());
		if ( existingValidator == null) {
			return validatorRepository.save(validator);
		} else {
			existingValidator.setValidator(validator.getValidator());
			return update(existingValidator);
		}
	}

	@Override
	public void delete(String id) {
		validatorRepository.deleteById(id);		
	}

	@Override
	public Validator update(Validator validator) {
		return this.validatorRepository.saveAndFlush(validator);
	}
}
