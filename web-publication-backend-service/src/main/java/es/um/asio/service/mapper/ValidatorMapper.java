package es.um.asio.service.mapper;

import java.util.List;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import es.um.asio.service.domain.Validator;
import es.um.asio.service.dto.ValidatorDto;
import es.um.asio.service.mapper.decorator.ValidatorMapperDecorator;
import es.um.asio.service.util.PageImplHelper;

@Mapper
@DecoratedWith(ValidatorMapperDecorator.class)
public interface ValidatorMapper {
	
	ValidatorDto convertValidatorToDto(Validator validator);
	
	List<ValidatorDto> convertValidatorListToDto(List<Validator> list);
	
	/**
	 * Convert Validator page to dto.
	 *
	 * @param page the page
	 * @return the page impl helper
	 */
	PageImplHelper<ValidatorDto> convertValidatorPageToDto(Page<Validator> page);
	

	/**
	 * Convert validator from dto.
	 *
	 * @param validatorDto validator data
	 * @return the validator
	 */
	Validator convertValidatorFromDto(ValidatorDto validatorDto);
}
