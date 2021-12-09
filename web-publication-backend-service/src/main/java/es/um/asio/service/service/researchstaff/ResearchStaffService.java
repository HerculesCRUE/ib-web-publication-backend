package es.um.asio.service.service.researchstaff;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.filter.researchstaff.ResearchStaffFilter;
import es.um.asio.service.model.Entity;
import es.um.asio.service.model.FusekiResponse;

public interface ResearchStaffService {

	Page<FusekiResponse> findPaginated(ResearchStaffFilter filter, Pageable pageable);

	Entity retrieveEntity(ResearchStaffFilter filter);

	String filtersChunk(ResearchStaffFilter filter);
}
