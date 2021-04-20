package es.um.asio.service.service.area;

import java.util.List;

import es.um.asio.service.filter.area.AreaFilter;

public interface AreaService {

	List<Object> getAreaByresearchGroup();

	String getAreasWithMoreProjects(AreaFilter filter);

}
