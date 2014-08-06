package com.github.aureliano.data.collector;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class CollectorManager {
	
	private static final Logger logger = Logger.getLogger(CollectorManager.class);

	private List<ICollector> collectors;
	
	public CollectorManager() {
		this.collectors = new ArrayList<ICollector>();
	}
	
	public void initCollectors() {
		logger.info("Initialize collectors for execution");
		logger.info("Total of collectors: " + this.collectors.size());
		
		for (ICollector collector : this.collectors) {
			this.init(collector);
		}
	}

	private void init(ICollector collector) {
		logger.debug("Execute before method");
		collector.before();
		
		logger.debug("Execute method");
		collector.execute();
		
		logger.debug("Execute after method");
		collector.after();
	}

	public List<ICollector> getCollectors() {
		return collectors;
	}

	public CollectorManager withCollectors(List<ICollector> collectors) {
		this.collectors = collectors;
		return this;
	}
	
	public CollectorManager addCollector(ICollector collector) {
		this.collectors.add(collector);
		return this;
	}
}