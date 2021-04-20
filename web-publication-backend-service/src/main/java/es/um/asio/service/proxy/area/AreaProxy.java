package es.um.asio.service.proxy.area;

import java.util.List;

import es.um.asio.service.dto.graphic.GraphicsDto;
import es.um.asio.service.filter.area.AreaFilter;

public interface AreaProxy {

	List<GraphicsDto> getAreaByresearchGroup();

	String getAreasWithMoreProjects(AreaFilter filter);

}
