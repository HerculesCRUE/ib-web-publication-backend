package es.um.asio.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import es.um.asio.service.domain.SparqlQuery;

@Repository
public interface SparqlQueryRepository
		extends JpaRepository<SparqlQuery, String>, JpaSpecificationExecutor<SparqlQuery> {
}
