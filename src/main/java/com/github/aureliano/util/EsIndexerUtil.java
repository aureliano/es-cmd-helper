package com.github.aureliano.util;

import org.elasticsearch.client.Client;

import com.github.aureliano.annotation.ESIndex;
import com.github.kzwang.osem.annotations.Indexable;
import com.github.kzwang.osem.api.ElasticSearchIndexer;
import com.github.kzwang.osem.impl.ElasticSearchIndexerImpl;

public final class EsIndexerUtil {

	private EsIndexerUtil() {
		super();
	}
	
	public static ElasticSearchIndexer getElasticSearchIndexer(Client client, String indexName) {
		return new ElasticSearchIndexerImpl(client, indexName);
	}
	
	public static ElasticSearchIndexer getElasticSearchIndexer(Client client, Class<?> indexClass) {
		validateIndexClass(indexClass);		
		String indexName = indexClass.getAnnotation(ESIndex.class).name();
		
		return getElasticSearchIndexer(client, indexName);
	}
	
	public static void validateIndexClass(Class<?> indexClass) {
		if (indexClass.getAnnotation(Indexable.class) == null) {
			throw new IllegalArgumentException(indexClass.getCanonicalName() + " is not an indexable class");
		}
		
		if (indexClass.getAnnotation(ESIndex.class) == null) {
			throw new IllegalArgumentException(indexClass.getCanonicalName() +
					" does not contain annotation " + ESIndex.class.getName());
		}
	}
	
	public static Class<?> getMappingBeanClass(String className) {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException(ex);
		}
	}
}