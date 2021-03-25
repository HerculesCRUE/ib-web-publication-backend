package es.um.asio.service.proxy.project.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.ProjectDto;
import es.um.asio.service.filter.project.ProjectFilter;
import es.um.asio.service.mapper.ProjectMapper;
import es.um.asio.service.proxy.project.ProjectProxy;
import es.um.asio.service.service.project.ProjectService;

/**
 * Implementación del Proxy para Proyectos
 *
 */
@Service
public class ProjectProxyImpl implements ProjectProxy {

	@Autowired
	private ProjectService service;

	@Autowired
	private ProjectMapper mapper;

	@Override
	public Page<ProjectDto> findPaginated(ProjectFilter filter, Pageable pageable) {
		return this.mapper.convertPageFusekiResponseToDto(this.service.findPaginated(filter, pageable));

	}

	@Override
	public String getArea() {
		return this.service.getArea();
	}

}
