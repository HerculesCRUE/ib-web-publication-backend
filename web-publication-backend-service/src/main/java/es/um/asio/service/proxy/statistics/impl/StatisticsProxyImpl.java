package es.um.asio.service.proxy.statistics.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.um.asio.service.proxy.statistics.StatisticsProxy;
import es.um.asio.service.service.statistcs.StatisticsService;

@Service
public class StatisticsProxyImpl implements StatisticsProxy {

	@Autowired
	private StatisticsService service;

	@Override
	public String topPublications() {
		return this.service.topPublications();
	}

	@Override
	public String topPatents() {
		return this.service.topPatents();
	}

}
