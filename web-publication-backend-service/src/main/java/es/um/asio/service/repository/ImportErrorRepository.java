package es.um.asio.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import es.um.asio.service.domain.ImportErrorEntity;

public interface ImportErrorRepository extends JpaRepository<ImportErrorEntity, String>, JpaSpecificationExecutor<ImportErrorEntity> {

}
