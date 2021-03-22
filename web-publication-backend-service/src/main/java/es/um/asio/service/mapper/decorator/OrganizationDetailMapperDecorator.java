package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.OrganizationDetailDto;
import es.um.asio.service.mapper.OrganizationDetailMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class OrganizationDetailMapperDecorator extends BaseMapperDecorator<OrganizationDetailDto>
		implements OrganizationDetailMapper {

	@Autowired
	@Qualifier("delegate")
	private OrganizationDetailMapper mapper;

	public OrganizationDetailMapperDecorator() {
		this.type = OrganizationDetailDto.class;
	}

	@Override
	public OrganizationDetailDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<OrganizationDetailDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<OrganizationDetailDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}

}
