package com.github.aureliano;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ElasticSearchConfigTest {
	
	@Before
	public void beforeTest() {
		ElasticSearchConfig.getInstance().reset();
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
	public void testMetadata() {
		ElasticSearchConfig configuration = ElasticSearchConfig.getInstance();
		Assert.assertEquals("127.0.0.1", configuration.getElasticSearchHost());
		Assert.assertEquals(9637, configuration.getTransportClientPort());
	}
}