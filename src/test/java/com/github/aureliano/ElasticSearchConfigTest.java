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
		writer.write("elastic.search.host=localhost");
		writer.newLine();
		writer.write("transport.client.port=9637");
		writer.newLine();
		writer.write("automatic.client.close=false");
		writer.flush();
		
		ElasticSearchConfig.loadConfigurationFromFile();
	}
	
	@After
	public void afterTest() {
		new File(ElasticSearchConfig.ELASTIC_SEARCH_PROPERTIES_FILE).delete();
	}
	
	@Test
	public void testElasticSearchDefaultConfig() {
		ElasticSearchConfig configuration = new ElasticSearchConfig();
		Assert.assertEquals("127.0.0.1", configuration.getElasticSearchHost());
		Assert.assertEquals(9300, configuration.getTransportClientPort());
		Assert.assertTrue(configuration.isAutomaticClientClose());
	}
	
	@Test
	public void testElasticSearchFileConfig() {
		ElasticSearchConfig configuration = ElasticSearchConfig.loadConfigurationFromFile();
		Assert.assertEquals("localhost", configuration.getElasticSearchHost());
		Assert.assertEquals(9637, configuration.getTransportClientPort());
		Assert.assertFalse(configuration.isAutomaticClientClose());
	}
	
	@Test
	public void testElasticSearchCodeConfig() {
		ElasticSearchConfig configuration = new ElasticSearchConfig()
			.withElasticSearchHost("124.5.6.112")
			.withTransportClientPort(9970);
		
		Assert.assertEquals("124.5.6.112", configuration.getElasticSearchHost());
		Assert.assertEquals(9970, configuration.getTransportClientPort());
		Assert.assertTrue(configuration.isAutomaticClientClose());
	}
}