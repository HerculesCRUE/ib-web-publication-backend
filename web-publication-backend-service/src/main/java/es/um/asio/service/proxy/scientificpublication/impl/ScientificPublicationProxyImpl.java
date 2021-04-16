package es.um.asio.service.proxy.scientificpublication.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.ScientificPublicationDto;
import es.um.asio.service.dto.graphic.GraphicsDto;
import es.um.asio.service.filter.scientificpublication.ScientificPublicationFilter;
import es.um.asio.service.mapper.GraphicsMapper;
import es.um.asio.service.mapper.ScientificPublicationMapper;
import es.um.asio.service.model.FusekiResponse;
import es.um.asio.service.proxy.scientificpublication.ScientificPublicationProxy;
import es.um.asio.service.service.scientificpublication.ScientificPublicationService;

@Service
public class ScientificPublicationProxyImpl implements ScientificPublicationProxy {

	@Autowired
	private ScientificPublicationService service;

	@Autowired
	private ScientificPublicationMapper mapper;

	@Autowired
	private GraphicsMapper graphicsMapper;

	@Override
	public Page<ScientificPublicationDto> findPaginated(ScientificPublicationFilter filter, Pageable pageable) {
		Page<FusekiResponse> result = this.service.findPaginated(filter, pageable);

		return this.mapper.convertPageFusekiResponseToDto(result);
	}

	@Override
	public ScientificPublicationDto find(String id) {
		List<ScientificPublicationDto> list = this.mapper.convertFusekiResponseToDto(this.service.find(id));

		return (list.isEmpty()) ? null : list.get(0);
	}

	@Override
	public List<GraphicsDto> publicationByPerson(String id) {
		List<GraphicsDto> list = this.graphicsMapper.convertFusekiResponseToDto(this.service.publicationByPerson(id));
		return (list.isEmpty()) ? null : list;
	}
}
