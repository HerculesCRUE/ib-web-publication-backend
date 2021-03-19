package es.um.asio.service.proxy.event.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.EventDetailDto;
import es.um.asio.service.dto.EventDto;
import es.um.asio.service.filter.event.EventFilter;
import es.um.asio.service.mapper.EventDetailMapper;
import es.um.asio.service.mapper.EventMapper;
import es.um.asio.service.proxy.event.EventProxy;
import es.um.asio.service.service.event.EventService;

/**
 * Implementación del Proxy para Eventos
 *
 */
@Service
public class EventProxyImpl implements EventProxy {

	@Autowired
	private EventService service;
	
	@Autowired
	private EventMapper mapper;
	
	@Autowired
	private EventDetailMapper detailMapper;
	
	@Override
	public Page<EventDto> findPaginated(EventFilter filter, Pageable pageable) {
		return this.mapper.convertPageFusekiResponseToDto(this.service.findPaginated(filter, pageable));
	}

	@Override
	public EventDetailDto find(String id, String type) {
		List<EventDetailDto> list = this.detailMapper.convertFusekiResponseToDto(this.service.find(id, type));
		return (list.isEmpty())? null : list.get(0);
	}
	
	

}
