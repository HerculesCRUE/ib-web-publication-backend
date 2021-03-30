package es.um.asio.service.proxy.otherpublication.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.OtherPublicationDto;
import es.um.asio.service.filter.otherpublication.OtherPublicationFilter;
import es.um.asio.service.mapper.OtherPublicationMapper;
import es.um.asio.service.proxy.otherpublication.OtherPublicationProxy;
import es.um.asio.service.service.otherpublication.OtherPublicationService;

@Service
public class OtherPublicationProxyImpl implements OtherPublicationProxy {

	@Autowired
	private OtherPublicationService service;
	
	@Autowired
	private OtherPublicationMapper mapper;
	
	@Override
	public Page<OtherPublicationDto> findPaginated(OtherPublicationFilter filter, Pageable pageable) {
		return this.mapper.convertPageFusekiResponseToDto(this.service.findPaginated(filter, pageable));
	}

	@Override
	public OtherPublicationDto find(String id, String type) {
		List<OtherPublicationDto> list = this.mapper.convertFusekiResponseToDto(this.service.find(id, type));
		return (list.isEmpty())? null : list.get(0);
	}
}
