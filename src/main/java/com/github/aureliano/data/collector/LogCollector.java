package com.github.aureliano.data.collector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class LogCollector implements ICollector {

	public LogCollector() {
		super();
	}
	
	@Override
	public void before() {}
	
	@Override
	public void after() {}
	
	public abstract Object parseEvent(Map<String, String> event);
	
	@Override
	public void execute() {
		File tmpDir = new File("tmp");
		if (!tmpDir.exists()) {
			tmpDir.mkdir();
		}
		
		File dataLog = new File(this.downloadDataLog());
		List<Object> events = new ArrayList<Object>();
		ObjectMapper mapper = new ObjectMapper();
		String line;
		
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(new FileInputStream(dataLog)));
			while ((line = reader.readLine()) != null) {
				Map<String, String> event = mapper.readValue(line, Map.class);
				Object dataObject = this.parseEvent(event);
				events.add(dataObject);
			}
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}		
		
		this.saveEventsOnElasticSearch(events);		
		this.recordCollectorExecutionLog();
	}
	
	protected String downloadDataLog() {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	protected void saveEventsOnElasticSearch(List<?> dataObject) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	protected void recordCollectorExecutionLog() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
}