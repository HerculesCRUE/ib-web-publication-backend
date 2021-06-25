package es.um.asio.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.um.asio.service.domain.importer.JobExecutionError;

@Repository
public interface JobExecutionErrorRepository extends JpaRepository<JobExecutionError, String>{

	List<JobExecutionError> findByJobExecutionId (final Long jobExecutionId);
}
