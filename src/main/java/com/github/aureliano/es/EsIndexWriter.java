package com.github.aureliano.es;

import org.elasticsearch.action.admin.indices.mapping.delete.DeleteMappingResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import com.github.aureliano.ElasticSearchConfig;
import com.github.aureliano.annotation.ESIndex;
import com.github.kzwang.osem.annotations.Indexable;
import com.github.kzwang.osem.api.ElasticSearchIndexer;
import com.github.kzwang.osem.impl.ElasticSearchIndexerImpl;

public class EsIndexWriter {

	private static EsIndexWriter instance;
	
	private EsIndexWriter() {
		super();
	}
	
	public static EsIndexWriter getInstance() {
		if (instance == null) {
			instance = new EsIndexWriter();
		}
		
		return instance;
	}
	
	public boolean indexExist(Class<?> indexClass) {
		return this.getElasticSearchIndexer(indexClass).indexExist();
	}
	
	public PutMappingResponse createMapping(Class<?> indexClass) {
		return this.getElasticSearchIndexer(indexClass).createMapping(indexClass);
	}
	
	public String getMapping(Class<?> indexClass) {
		return this.getElasticSearchIndexer(indexClass).getMapping(indexClass);
	}
	
	public DeleteMappingResponse deleteMapping(Class<?> indexClass) {
		return this.getElasticSearchIndexer(indexClass).deleteMapping(indexClass);
	}
	
	private ElasticSearchIndexer getElasticSearchIndexer(Class<?> indexClass) {
		this.validateIndexClass(indexClass);
		
		String indexName = indexClass.getAnnotation(ESIndex.class).name();
		ElasticSearchConfig esConfig = ElasticSearchConfig.getInstance();
		Client client = new TransportClient().addTransportAddress(
				new InetSocketTransportAddress(esConfig.getElasticSearchHost(), esConfig.getTransportClientPort()));
		
		return new ElasticSearchIndexerImpl(client, indexName);
	}
	
	private void validateIndexClass(Class<?> indexClass) {
		if (indexClass.getAnnotation(Indexable.class) == null) {
			throw new IllegalArgumentException(indexClass.getCanonicalName() + " is not an indexable class");
		}
		
		if (indexClass.getAnnotation(ESIndex.class) == null) {
			throw new IllegalArgumentException(indexClass.getCanonicalName() +
					" does not contain annotation " + ESIndex.class.getName());
		}
	}
}