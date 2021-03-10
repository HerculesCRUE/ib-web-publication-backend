package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.DocumentDetailDto;
import es.um.asio.service.mapper.DocumentDetailMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class DocumentDetailMapperDecorator extends BaseMapperDecorator<DocumentDetailDto> implements DocumentDetailMapper {

	@Autowired
    @Qualifier("delegate")
	private DocumentDetailMapper mapper;
	
	public DocumentDetailMapperDecorator() {
		this.type = DocumentDetailDto.class;
	}
	
	@Override
	public DocumentDetailDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<DocumentDetailDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<DocumentDetailDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
