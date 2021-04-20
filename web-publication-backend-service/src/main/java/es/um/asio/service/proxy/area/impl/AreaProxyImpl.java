package es.um.asio.service.proxy.area.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.graphic.GraphicsDto;
import es.um.asio.service.filter.area.AreaFilter;
import es.um.asio.service.mapper.GraphicsMapper;
import es.um.asio.service.proxy.area.AreaProxy;
import es.um.asio.service.service.area.AreaService;

@Service
public class AreaProxyImpl implements AreaProxy {

	@Autowired
	private AreaService service;

	@Autowired
	private GraphicsMapper graphicsMapper;

	@Override
	public List<GraphicsDto> getAreaByresearchGroup() {
		List<GraphicsDto> list = this.graphicsMapper.convertFusekiResponseToDto(this.service.getAreaByresearchGroup());
		return (list.isEmpty()) ? null : list;
	}

	@Override
	public String getAreasWithMoreProjects(AreaFilter filter) {
		return service.getAreasWithMoreProjects(filter);
	}
}
