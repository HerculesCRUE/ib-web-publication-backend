package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.EmploymentContractDto;
import es.um.asio.service.mapper.EmploymentContractMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class EmploymentContractMapperDecorator extends BaseMapperDecorator<EmploymentContractDto> implements EmploymentContractMapper {

	@Autowired
    @Qualifier("delegate")
	private EmploymentContractMapper mapper;
	
	public EmploymentContractMapperDecorator() {
		this.type = EmploymentContractDto.class;
	}
	
	@Override
	public EmploymentContractDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<EmploymentContractDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<EmploymentContractDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
