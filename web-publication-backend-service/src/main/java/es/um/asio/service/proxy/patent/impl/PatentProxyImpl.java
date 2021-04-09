package es.um.asio.service.proxy.patent.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.PatentDetailDto;
import es.um.asio.service.dto.PatentDto;
import es.um.asio.service.dto.graphic.PatentByOrganizationDto;
import es.um.asio.service.filter.patent.PatentFilter;
import es.um.asio.service.mapper.PatentByOrganizationMapper;
import es.um.asio.service.mapper.PatentDetailMapper;
import es.um.asio.service.mapper.PatentMapper;
import es.um.asio.service.proxy.patent.PatentProxy;
import es.um.asio.service.service.patent.PatentService;

/**
 * Implementación del Proxy para Patentes
 *
 */
@Service
public class PatentProxyImpl implements PatentProxy {

	@Autowired
	private PatentService service;

	@Autowired
	private PatentMapper mapper;

	@Autowired
	private PatentDetailMapper detailMapper;

	@Autowired
	private PatentByOrganizationMapper patentByOrganizationMapper;

	@Override
	public Page<PatentDto> findPaginated(PatentFilter filter, Pageable pageable) {
		return this.mapper.convertPageFusekiResponseToDto(this.service.findPaginated(filter, pageable));
	}

	@Override
	public List<PatentByOrganizationDto> getbyOrganization() {
		List<PatentByOrganizationDto> list = this.patentByOrganizationMapper
				.convertFusekiResponseToDto(this.service.getbyOrganization());
		return (list.isEmpty()) ? null : list;
	}

	@Override
	public PatentDetailDto find(String id) {
		List<PatentDetailDto> list = this.detailMapper.convertFusekiResponseToDto(this.service.find(id));
		return (list.isEmpty()) ? null : list.get(0);
	}

}
