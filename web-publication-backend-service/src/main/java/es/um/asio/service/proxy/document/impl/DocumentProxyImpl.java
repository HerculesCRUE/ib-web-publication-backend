package es.um.asio.service.proxy.document.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.DocumentDetailDto;
import es.um.asio.service.dto.DocumentDto;
import es.um.asio.service.filter.document.DocumentFilter;
import es.um.asio.service.mapper.DocumentDetailMapper;
import es.um.asio.service.mapper.DocumentMapper;
import es.um.asio.service.proxy.document.DocumentProxy;
import es.um.asio.service.service.document.DocumentService;

/**
 * Implementación del Proxy para Documentos
 *
 */
@Service
public class DocumentProxyImpl implements DocumentProxy {

	@Autowired
	private DocumentService service;
	
	@Autowired
	private DocumentMapper mapper;
	
	@Autowired
	private DocumentDetailMapper detailMapper;
	
	@Override
	public Page<DocumentDto> findPaginated(DocumentFilter filter, Pageable pageable) {
		return this.mapper.convertPageFusekiResponseToDto(this.service.findPaginated(filter, pageable));
	}

	@Override
	public DocumentDetailDto find(String id, String type) {
		List<DocumentDetailDto> list = this.detailMapper.convertFusekiResponseToDto(this.service.find(id, type));
		return (list.isEmpty())? null : list.get(0);
	}
	
	

}
