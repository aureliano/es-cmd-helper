package com.github.aureliano.es;

import org.junit.Assert;
import org.junit.Test;

public class EsIndexWriterTest {
	
	@Test(expected = NullPointerException.class)
	public void testMapIndexNullClass() {
		EsIndexDataStructure.createMapping(null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMapIndexNonIndexableClass() {
		try {
			EsIndexDataStructure.createMapping(null, Object.class);
		} catch (RuntimeException ex) {
			Assert.assertEquals(Object.class.getCanonicalName() + " is not an indexable class", ex.getMessage());
			throw ex;
		}
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetIndexNullClass() {
		EsIndexDataStructure.getMapping(null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetIndexNonIndexableClass() {
		try {
			EsIndexDataStructure.getMapping(null, Object.class);
		} catch (RuntimeException ex) {
			Assert.assertEquals(Object.class.getCanonicalName() + " is not an indexable class", ex.getMessage());
			throw ex;
		}
	}
	
	@Test(expected = NullPointerException.class)
	public void testDeleteIndexNullClass() {
		EsIndexDataStructure.deleteMapping(null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDeleteIndexNonIndexableClass() {
		try {
			EsIndexDataStructure.deleteMapping(null, Object.class);
		} catch (RuntimeException ex) {
			Assert.assertEquals(Object.class.getCanonicalName() + " is not an indexable class", ex.getMessage());
			throw ex;
		}
	}
}