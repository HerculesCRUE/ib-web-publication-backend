package es.um.asio.service.proxy.validator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.ValidatorDto;
import es.um.asio.service.mapper.ValidatorMapper;
import es.um.asio.service.proxy.validator.ValidatorProxy;
import es.um.asio.service.service.validator.ValidatorService;

@Service
public class ValidatorProxyImpl implements ValidatorProxy {
	
	@Autowired
	private ValidatorService validatorService;

	@Autowired
	ValidatorMapper mapper;
	

	@Override
	public Page<ValidatorDto> findPaginated(Pageable pageable) {
		return this.mapper.convertValidatorPageToDto(this.validatorService.findPaginated(pageable));
	}

	@Override
	public ValidatorDto save(ValidatorDto validatorDto) {
		return this.mapper.convertValidatorToDto(
				this.validatorService.save(this.mapper.convertValidatorFromDto(validatorDto)));
	}

	@Override
	public ValidatorDto update(ValidatorDto validatorDto) {
		return this.mapper.convertValidatorToDto(
				this.validatorService.update(this.mapper.convertValidatorFromDto(validatorDto)));
	}

	@Override
	public void delete(String id) {
		this.validatorService.delete(id);		
	}
}
