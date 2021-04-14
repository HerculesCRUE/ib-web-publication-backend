package es.um.asio.service.proxy.project.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.PersonParticipantDto;
import es.um.asio.service.dto.ProjectByModalityDto;
import es.um.asio.service.dto.ProjectDetailDto;
import es.um.asio.service.dto.ProjectDto;
import es.um.asio.service.filter.person.PersonFilter;
import es.um.asio.service.filter.project.ProjectFilter;
import es.um.asio.service.mapper.PersonParticipantMapper;
import es.um.asio.service.mapper.ProjectByModalityMapper;
import es.um.asio.service.mapper.ProjectDetailMapper;
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

	@Autowired
	private ProjectByModalityMapper projectByModalityMapper;

	@Autowired
	private ProjectDetailMapper detailMapper;
	
	@Autowired
	private PersonParticipantMapper personParticipantMapper;

	@Override
	public Page<ProjectDto> findPaginated(ProjectFilter filter, Pageable pageable) {
		return this.mapper.convertPageFusekiResponseToDto(this.service.findPaginated(filter, pageable));

	}

	@Override
	public ProjectDetailDto find(String id) {
		List<ProjectDetailDto> list = this.detailMapper.convertFusekiResponseToDto(this.service.find(id));
		return (list.isEmpty()) ? null : list.get(0);
	}

	@Override
	public List<ProjectByModalityDto> getbyModality() {
		List<ProjectByModalityDto> list = this.projectByModalityMapper
				.convertFusekiResponseToDto(this.service.getbyModality());
		return (list.isEmpty()) ? null : list;
	}

	@Override
	public Page<PersonParticipantDto> getParticipants(String id, final PersonFilter filter, final Pageable pageable) {
		return this.personParticipantMapper.convertPageFusekiResponseToDto(this.service.getParticipants(id, filter, pageable));
	}

}
