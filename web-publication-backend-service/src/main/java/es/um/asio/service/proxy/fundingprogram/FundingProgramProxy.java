package es.um.asio.service.proxy.fundingprogram;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.FundingProgramDto;
import es.um.asio.service.filter.fundingprogram.FundingProgramFilter;

public interface FundingProgramProxy {
	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<FundingProgramDto>
	 */
	Page<FundingProgramDto> findPaginated(FundingProgramFilter filter, Pageable pageable);
}
