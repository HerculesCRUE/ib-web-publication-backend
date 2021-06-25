package es.um.asio.service.service.dataimporter.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.um.asio.service.domain.importer.JobExecutionError;
import es.um.asio.service.repository.JobExecutionErrorRepository;
import es.um.asio.service.service.dataimporter.DataImporterService;

@Service
public class DataImporterServiceImpl implements DataImporterService {

	@Autowired
	private JobExecutionErrorRepository repository;

	@Override
	public List<JobExecutionError> findErrors(Long id) {
		return repository.findByJobExecutionId(id);
	}
}
