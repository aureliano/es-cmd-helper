package com.github.aureliano;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ElasticSearchConfigTest {
	 
	@Before
	public void beforeTest() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(ElasticSearchConfig.ELASTIC_SEARCH_PROPERTIES_FILE));
		writer.write("elastic.search.host=127.0.0.1");
		writer.newLine();
		writer.write("transport.client.port=9637");
		writer.flush();
		
		ElasticSearchConfig.getInstance().reset();
	}
	
	@After
	public void afterTest() {
		new File(ElasticSearchConfig.ELASTIC_SEARCH_PROPERTIES_FILE).delete();
	}
	
	@Test
	public void testGetInstance() {
		ElasticSearchConfig configuration = ElasticSearchConfig.getInstance();
		Assert.assertNotNull(configuration);
		Assert.assertEquals(configuration, ElasticSearchConfig.getInstance());
		Assert.assertTrue(configuration == ElasticSearchConfig.getInstance());
		
		configuration.setElasticSearchHost("localhost");
		Assert.assertEquals(configuration, ElasticSearchConfig.getInstance());
		
		configuration.setTransportClientPort(9200);
		Assert.assertEquals(configuration, ElasticSearchConfig.getInstance());
	}
	
	@Test
	public void testElasticSearchConfig() {
		ElasticSearchConfig configuration = ElasticSearchConfig.getInstance();
		Assert.assertEquals("127.0.0.1", configuration.getElasticSearchHost());
		Assert.assertEquals(9637, configuration.getTransportClientPort());
	}
}