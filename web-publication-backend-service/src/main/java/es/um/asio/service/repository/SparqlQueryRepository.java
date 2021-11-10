package es.um.asio.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.um.asio.service.domain.SparqlQuery;

@Repository
public interface SparqlQueryRepository
		extends JpaRepository<SparqlQuery, String>, JpaSpecificationExecutor<SparqlQuery> {

	@Query(value = "select p.user from (SELECT *,case\r\n" + "when is2.type ='importDataSetJob' then 1 \r\n"
			+ "when is2.type ='importCvnJob' then 2\r\n" + "when is2.type ='importSgiJob' then 3\r\n"
			+ "when is2.type ='importCerifJob' then 4\r\n" + "end tipo\r\n" + "FROM asio_jobs.importer_schedule is2\r\n"
			+ "WHERE is2.deleted_date is NULL\r\n"
			+ ") p where p.tipo=(select jobType from `input`.ImportResult where endTime is not null AND version = (select "
			+ "MAX(a.version) from `input`.ImportResult a));", nativeQuery = true)
	String lastUserImportJob();
}
