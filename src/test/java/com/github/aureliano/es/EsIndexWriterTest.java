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
	
	@Test(expected = NullPointerException.class)
	public void testMapIndexNullClass() {
		EsIndexWriter.getInstance().createMapping(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMapIndexNonIndexableClass() {
		try {
			EsIndexWriter.getInstance().createMapping(Object.class);
		} catch (RuntimeException ex) {
			Assert.assertEquals(Object.class.getCanonicalName() + " is not an indexable class", ex.getMessage());
			throw ex;
		}
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetIndexNullClass() {
		EsIndexWriter.getInstance().getMapping(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetIndexNonIndexableClass() {
		try {
			EsIndexWriter.getInstance().getMapping(Object.class);
		} catch (RuntimeException ex) {
			Assert.assertEquals(Object.class.getCanonicalName() + " is not an indexable class", ex.getMessage());
			throw ex;
		}
	}
	
	@Test(expected = NullPointerException.class)
	public void testDeleteIndexNullClass() {
		EsIndexWriter.getInstance().deleteMapping(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDeleteIndexNonIndexableClass() {
		try {
			EsIndexWriter.getInstance().deleteMapping(Object.class);
		} catch (RuntimeException ex) {
			Assert.assertEquals(Object.class.getCanonicalName() + " is not an indexable class", ex.getMessage());
			throw ex;
		}
	}
}