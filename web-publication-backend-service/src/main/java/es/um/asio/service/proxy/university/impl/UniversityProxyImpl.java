package es.um.asio.service.proxy.university.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.UniversityDto;
import es.um.asio.service.dto.graphic.GraphicsDto;
import es.um.asio.service.filter.university.UniversityFilter;
import es.um.asio.service.mapper.GraphicsMapper;
import es.um.asio.service.mapper.UniversityMapper;
import es.um.asio.service.proxy.university.UniversityProxy;
import es.um.asio.service.service.university.UniversityService;

/**
 * Implementación del Proxy para Universidades
 *
 */
@Service
public class UniversityProxyImpl implements UniversityProxy {

	@Autowired
	private UniversityService service;

	@Autowired
	private UniversityMapper mapper;

	@Autowired
	private GraphicsMapper graphicsMapper;

	@Override
	public Page<UniversityDto> findPaginated(UniversityFilter filter, Pageable pageable) {
		return this.mapper.convertPageFusekiResponseToDto(this.service.findPaginated(filter, pageable));
	}

	@Override
	public String getQualitySeal() {
		return this.service.getQualitySeal();
	}

	@Override
	public List<GraphicsDto> organizationByType() {
		List<GraphicsDto> list = this.graphicsMapper.convertFusekiResponseToDto(this.service.organizationByType());
		return (list.isEmpty()) ? null : list;
	}

}
