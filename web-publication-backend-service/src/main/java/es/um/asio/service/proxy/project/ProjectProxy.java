package es.um.asio.service.proxy.project;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.um.asio.service.dto.ProjectDetailDto;
import es.um.asio.service.dto.ProjectDto;
import es.um.asio.service.dto.graphic.GraphicsDto;
import es.um.asio.service.filter.project.ProjectFilter;

/**
 * Interfaz Proxy Proyecto
 *
 */
public interface ProjectProxy {

	/**
	 * Método de búsqueda
	 * 
	 * @param filter
	 * @param pageable
	 * @return Page<ProjectDto>
	 */
	Page<ProjectDto> findPaginated(ProjectFilter filter, Pageable pageable);

	ProjectDetailDto find(String id);

	List<GraphicsDto> getbyModality();
}
