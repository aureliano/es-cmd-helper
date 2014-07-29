package com.github.aureliano.es;

import org.junit.Assert;
import org.junit.Test;

public class EsIndexWriterTest {

	@Test
	public void testGetInstance() {
		EsIndexWriter writer = EsIndexWriter.getInstance();
		Assert.assertNotNull(writer);
		Assert.assertEquals(writer, EsIndexWriter.getInstance());
		Assert.assertTrue(writer == EsIndexWriter.getInstance());
	}
}