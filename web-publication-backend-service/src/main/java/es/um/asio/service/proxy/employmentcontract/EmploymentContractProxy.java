package es.um.asio.service.proxy.employmentcontract;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.EmploymentContractDto;
import es.um.asio.service.filter.employmentcontract.EmploymentContractFilter;

public interface EmploymentContractProxy {

	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<EmploymentContractDto>
	 */
	Page<EmploymentContractDto> findPaginated(EmploymentContractFilter filter, Pageable pageable);
}
