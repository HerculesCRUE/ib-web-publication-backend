package es.um.asio.service.proxy.statistics.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.um.asio.service.dto.graphic.GraphicsDto;
import es.um.asio.service.mapper.GraphicsMapper;
import es.um.asio.service.proxy.statistics.StatisticsProxy;
import es.um.asio.service.service.statistcs.StatisticsService;

@Service
public class StatisticsProxyImpl implements StatisticsProxy {

	@Autowired
	private StatisticsService service;

	@Autowired
	private GraphicsMapper graphicsMapper;

	@Override
	public List<GraphicsDto> articlesByPublishedIn() {
		List<GraphicsDto> list = this.graphicsMapper.convertFusekiResponseToDto(this.service.articlesByPublishedIn());
		return (list.isEmpty()) ? null : list;
	}

	@Override
	public List<GraphicsDto> projectByClassification() {
		List<GraphicsDto> list = this.graphicsMapper.convertFusekiResponseToDto(this.service.projectByClassification());
		return (list.isEmpty()) ? null : list;
	}

}
