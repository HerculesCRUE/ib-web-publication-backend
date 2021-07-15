package es.um.asio.service.mapper.decorator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.domain.Validator;
import es.um.asio.service.dto.ValidatorDto;
import es.um.asio.service.mapper.ValidatorMapper;
import es.um.asio.service.util.PageImplHelper;

public abstract class ValidatorMapperDecorator implements ValidatorMapper {
	
	/** The delegate. */
	@Autowired
	@Qualifier("delegate")
	private ValidatorMapper delegate;
	
	@Override
	public List<ValidatorDto> convertValidatorListToDto(List<Validator> list) {
		if (list == null) {
			return null;
		}

		List<ValidatorDto> list1 = new ArrayList<>(list.size());
		for (Validator validator : list) {
			list1.add(convertValidatorToDto(validator));
		}

		return list1;
	}
	
	@Override
	public ValidatorDto convertValidatorToDto(Validator validator) {
		if (validator == null) {
			return null;
		}
		ValidatorDto validatorDto = this.delegate.convertValidatorToDto(validator);

		validatorDto.setId(validator.getEntityId());

		return validatorDto;
	}

	@Override
	public PageImplHelper<ValidatorDto> convertValidatorPageToDto(Page<Validator> page) {
		if (page == null) {
			return null;
		}

		final List<ValidatorDto> list = this.convertValidatorListToDto(page.getContent());

		return new PageImplHelper<>(list, page.getPageable(), page.getTotalElements());
	}

	@Override
	public Validator convertValidatorFromDto(ValidatorDto validatorDto) {
		if (validatorDto == null) {
			return null;
		}
		Validator validator = this.delegate.convertValidatorFromDto(validatorDto);

		validator.setEntityId(validatorDto.getId());

		return validator;
	}

	
	
}
