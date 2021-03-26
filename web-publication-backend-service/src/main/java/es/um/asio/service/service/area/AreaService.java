package es.um.asio.service.service.area;

import es.um.asio.service.filter.area.AreaFilter;

public interface AreaService {

	String getAreaWithYear(AreaFilter filter);

	String getAreasWithMoreProjects(AreaFilter filter);

}
