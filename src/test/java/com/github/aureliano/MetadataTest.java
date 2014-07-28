package com.github.aureliano;

import junit.framework.Assert;

import org.junit.Test;

public class MetadataTest {

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