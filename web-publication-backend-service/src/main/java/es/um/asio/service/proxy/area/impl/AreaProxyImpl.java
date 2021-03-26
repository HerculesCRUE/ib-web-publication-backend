package es.um.asio.service.proxy.area.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.um.asio.service.filter.area.AreaFilter;
import es.um.asio.service.proxy.area.AreaProxy;
import es.um.asio.service.service.area.AreaService;

@Service
public class AreaProxyImpl implements AreaProxy {

	@Autowired
	private AreaService service;

	@Override
	public String getAreaWithYear(AreaFilter filter) {
		return service.getAreaWithYear(filter);
	}

	@Override
	public String getAreasWithMoreProjects(AreaFilter filter) {
		return service.getAreasWithMoreProjects(filter);
	}
}
