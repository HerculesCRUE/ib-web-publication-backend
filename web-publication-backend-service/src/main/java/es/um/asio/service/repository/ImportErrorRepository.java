package es.um.asio.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import es.um.asio.service.domain.ImportErrorEntity;

@Repository
public interface ImportErrorRepository extends JpaRepository<ImportErrorEntity, String>, JpaSpecificationExecutor<ImportErrorEntity> {

	List<ImportErrorEntity> findByJobExecutionId (final String jobExecutionId);
}
