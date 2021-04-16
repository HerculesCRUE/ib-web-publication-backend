package es.um.asio.service.mapper.decorator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import es.um.asio.service.dto.KnowledgeAreaDto;
import es.um.asio.service.mapper.KnowledgeAreaMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.util.PageImplHelper;

public class KnowledgeAreaMapperDecorator extends BaseMapperDecorator<KnowledgeAreaDto> implements KnowledgeAreaMapper {

	@Autowired
    @Qualifier("delegate")
	private KnowledgeAreaMapper mapper;
	
	public KnowledgeAreaMapperDecorator() {
		this.type = KnowledgeAreaDto.class;
	}
	
	@Override
	public KnowledgeAreaDto convertFusekiObjectToDto(Object response) {
		return super.convertFusekiObjectToDto(response);
	}

	@Override
	public List<KnowledgeAreaDto> convertFusekiResponseToDto(List<Object> response) {
		return super.convertFusekiResponseToDto(response);
	}

	@Override
	public PageImplHelper<KnowledgeAreaDto> convertPageFusekiResponseToDto(Page<FusekiResponse> page) {
		return super.convertPageFusekiResponseToDto(page);
	}
}
