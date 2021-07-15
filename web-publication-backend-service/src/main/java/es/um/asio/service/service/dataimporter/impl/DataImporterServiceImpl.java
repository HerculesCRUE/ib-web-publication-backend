package es.um.asio.service.service.dataimporter.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.um.asio.service.domain.ImportErrorEntity;
import es.um.asio.service.repository.ImportErrorRepository;
import es.um.asio.service.service.dataimporter.DataImporterService;

@Service
public class DataImporterServiceImpl implements DataImporterService {

	@Autowired
	private ImportErrorRepository repository;

	@Override
	public List<ImportErrorEntity> findErrors(String id) {
		return repository.findByJobExecutionId(id);
	}
}
