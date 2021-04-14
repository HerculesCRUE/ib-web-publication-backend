package es.um.asio.service.proxy.person.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.PersonDetailDto;
import es.um.asio.service.dto.PersonDto;
import es.um.asio.service.dto.graphic.GraphicsDto;
import es.um.asio.service.filter.person.PersonFilter;
import es.um.asio.service.mapper.GraphicsMapper;
import es.um.asio.service.mapper.PersonDetailMapper;
import es.um.asio.service.mapper.PersonMapper;
import es.um.asio.service.proxy.person.PersonProxy;
import es.um.asio.service.service.person.PersonService;

/**
 * Implementación del Proxy para Personas
 *
 */
@Service
public class PersonProxyImpl implements PersonProxy {

	@Autowired
	private PersonService service;

	@Autowired
	private PersonMapper mapper;

	@Autowired
	private PersonDetailMapper detailMapper;

	@Autowired
	private GraphicsMapper personByAreaMapper;

	@Override
	public Page<PersonDto> findPaginated(PersonFilter filter, Pageable pageable) {
		return this.mapper.convertPageFusekiResponseToDto(this.service.findPaginated(filter, pageable));
	}

	@Override
	public PersonDetailDto find(String id) {
		List<PersonDetailDto> list = this.detailMapper.convertFusekiResponseToDto(this.service.find(id));
		return (list.isEmpty()) ? null : list.get(0);
	}

	@Override
	public List<GraphicsDto> getArea() {
		List<GraphicsDto> list = this.personByAreaMapper.convertFusekiResponseToDto(this.service.getArea());
		return (list.isEmpty()) ? null : list;
	}
}
