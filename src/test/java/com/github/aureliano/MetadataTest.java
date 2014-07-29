package com.github.aureliano;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class MetadataTest {
	
	@Before
	public void beforeTest() {
		Metadata.getInstance().reset();
	}

	@Test
	public void testGetInstance() {
		Metadata metadata = Metadata.getInstance();
		Assert.assertNotNull(metadata);
		Assert.assertEquals(metadata, Metadata.getInstance());
		Assert.assertTrue(metadata == Metadata.getInstance());
		
		metadata.setAppName("test");
		Assert.assertEquals(metadata, Metadata.getInstance());
		
		metadata.setDocumentation("/path/to/documentation");
		Assert.assertEquals(metadata, Metadata.getInstance());
	}
	
	@Test
	public void testMetadata() {
		Metadata m = Metadata.getInstance();
		Assert.assertEquals("es-cmd-helper", m.getAppName());
		Assert.assertEquals("0.1.0", m.getReleaseVersion());
		Assert.assertEquals("2014-07-28", m.getReleaseDate());
		Assert.assertEquals("1.6", m.getJavaVersion());

		Assert.assertEquals("https://github.com/aureliano/es-cmd-helper", m.getProjectHome());
		Assert.assertEquals("https://github.com/aureliano/es-cmd-helper", m.getScmHome());
		Assert.assertEquals("https://github.com/aureliano/es-cmd-helper/wiki", m.getDocumentation());
		Assert.assertEquals("http://www.javadoc.com/project", m.getJavadoc());
	}
}