package es.um.asio.service.proxy.dataimporter.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.um.asio.service.domain.ImportErrorEntity;
import es.um.asio.service.proxy.dataimporter.DataImporterProxy;
import es.um.asio.service.service.dataimporter.DataImporterService;

@Service
public class DataImporterProxyImpl implements DataImporterProxy{
	
	@Autowired
	private DataImporterService dataImporterService;

	@Override
	public List<String> findErrors(String id) {		
		return dataImporterService.findErrors(id)
				.stream()
				.map(ImportErrorEntity::getDescription)				
				.collect(Collectors.toList());
				
	}	
}
