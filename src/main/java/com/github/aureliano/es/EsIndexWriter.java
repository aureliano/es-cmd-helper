package com.github.aureliano.es;

import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
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

/**
 * ElasticSearch index writer. Encapsulate commands that interact
 * with core ElasticSearch index writer.
 */
public class EsIndexWriter {

	private static EsIndexWriter instance;
	
	private EsIndexWriter() {
		super();
	}
		
	/**
	 * Single method. It ensures there's just one instance of this object.
	 * 
	 * @return The EsIndexWriter singleton.
	 */
	public static EsIndexWriter getInstance() {
		if (instance == null) {
			instance = new EsIndexWriter();
		}
		
		return instance;
	}
	
	/**
	 * Verify if an index exist.
	 * 
	 * @param Mapping-type class <indexClass>
	 * @return If the index exist or not <boolean>
	 */
	public boolean indexExist(Class<?> indexClass) {
		return this.getElasticSearchIndexer(indexClass).indexExist();
	}
	
	/**
	 * Verify if an index exist.
	 * 
	 * @param Index name <indexName>.
	 * @return If the index exist or not <boolean>
	 */
	public boolean indexExist(String indexName) {
		return this.getElasticSearchIndexer(indexName).indexExist();
	}
	
	/**
	 * Create an index.
	 * 
	 * @param The index name <indexName>
	 * @return The response {@link org.elasticsearch.action.admin.indices.create.CreateIndexResponse}
	 */
	public CreateIndexResponse createIndex(String indexName) {
		return this.getElasticSearchIndexer(indexName).createIndex();
	}
	
	/**
	 * Delete an index.
	 * 
	 * @param The index name <indexName>
	 * @return The response {@link org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse}
	 */
	public DeleteIndexResponse deleteIndex(String indexName) {
		return this.getElasticSearchIndexer(indexName).deleteIndex();
	}
	
	/**
	 * Put a mapping type on index. That means whether type does not exist
	 * it will create one another way it'll replace (update) it.
	 * 
	 * @param Mapping-type class <indexClass>
	 * @return The response {@link org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse}
	 */
	public PutMappingResponse createMapping(Class<?> indexClass) {
		return this.getElasticSearchIndexer(indexClass).createMapping(indexClass);
	}
	
	/**
	 * Retrieve index type mapping.
	 * 
	 * @param Mapping-type class <indexClass>
	 * @return JSON mapping as text
	 */
	public String getMapping(Class<?> indexClass) {
		return this.getElasticSearchIndexer(indexClass).getMapping(indexClass);
	}
	
	/**
	 * Delete a mapping type from index.
	 * 
	 * @param Mapping-type class <indexClass>
	 * @return The response {@link org.elasticsearch.action.admin.indices.mapping.delete.DeleteMappingResponse}
	 */
	public DeleteMappingResponse deleteMapping(Class<?> indexClass) {
		return this.getElasticSearchIndexer(indexClass).deleteMapping(indexClass);
	}
	
	private ElasticSearchIndexer getElasticSearchIndexer(Class<?> indexClass) {
		this.validateIndexClass(indexClass);
		
		String indexName = indexClass.getAnnotation(ESIndex.class).name();
		return this.getElasticSearchIndexer(indexName);
	}
	
	private ElasticSearchIndexer getElasticSearchIndexer(String indexName) {
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