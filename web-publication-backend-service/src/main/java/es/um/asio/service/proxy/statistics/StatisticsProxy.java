package es.um.asio.service.proxy.statistics;

import java.util.List;

import es.um.asio.service.dto.graphic.GraphicsDto;

public interface StatisticsProxy {

	List<GraphicsDto> articlesByPublishedIn();

	List<GraphicsDto> projectByClassification();

}
