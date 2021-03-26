package es.um.asio.service.proxy.area;

import es.um.asio.service.filter.area.AreaFilter;

public interface AreaProxy {

	String getAreaWithYear(AreaFilter filter);

	String getAreasWithMoreProjects(AreaFilter filter);

}
