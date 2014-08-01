package com.github.aureliano.es;

import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.delete.DeleteMappingResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.client.Client;

import com.github.aureliano.util.EsIndexerUtil;

/**
 * ElasticSearch index data manipulator. Encapsulate commands that interact
 * with core ElasticSearch indexer in data structure scope: index and mapping types.
 */
public class EsIndexDataStructure {

	/**
	 * Verify if an index exist.
	 * 
	 * @param indexClass - Mapping-type class
	 * @return If the index exist or not <boolean>
	 */
	public static boolean indexExist(Client client, Class<?> indexClass) {
		return EsIndexerUtil.getElasticSearchIndexer(client, indexClass).indexExist();
	}
	
	/**
	 * Verify if an index exist.
	 * 
	 * @param client
	 * @param indexName - Index name
	 * @return If the index exist or not <boolean>
	 */
	public static boolean indexExist(Client client, String indexName) {
		return EsIndexerUtil.getElasticSearchIndexer(client, indexName).indexExist();
	}
	
	/**
	 * Create an index.
	 * 
	 * @param client
	 * @param indexClass - Mapping-type class
	 * @return The response {@link org.elasticsearch.action.admin.indices.create.CreateIndexResponse}
	 */
	public static CreateIndexResponse createIndex(Client client, Class<?> indexClass) {
		return EsIndexerUtil.getElasticSearchIndexer(client, indexClass).createIndex();
	}
	
	/**
	 * Create an index.
	 * 
	 * @param client
	 * @param indexName - The index name
	 * @return The response {@link org.elasticsearch.action.admin.indices.create.CreateIndexResponse}
	 */
	public static CreateIndexResponse createIndex(Client client, String indexName) {
		return EsIndexerUtil.getElasticSearchIndexer(client, indexName).createIndex();
	}
	
	/**
	 * Delete an index.
	 * 
	 * @param client
	 * @param indexClass - Mapping-type class
	 * @return The response {@link org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse}
	 */
	public static DeleteIndexResponse deleteIndex(Client client, Class<?> indexClass) {
		return EsIndexerUtil.getElasticSearchIndexer(client, indexClass).deleteIndex();
	}
	
	/**
	 * Delete an index.
	 * 
	 * @param client
	 * @param indexName - The index name
	 * @return The response {@link org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse}
	 */
	public static DeleteIndexResponse deleteIndex(Client client, String indexName) {
		return EsIndexerUtil.getElasticSearchIndexer(client, indexName).deleteIndex();
	}
	
	/**
	 * Put a mapping type on index. That means whether type does not exist
	 * it will create one another way it'll replace (update) it.
	 * 
	 * @param client
	 * @param indexClass - Mapping-type class
	 * @return The response {@link org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse}
	 */
	public static PutMappingResponse createMapping(Client client, Class<?> indexClass) {
		return EsIndexerUtil.getElasticSearchIndexer(client, indexClass).createMapping(indexClass);
	}
	
	/**
	 * Retrieve index type mapping.
	 * 
	 * @param client
	 * @param indexClass - Mapping-type class
	 * @return JSON mapping as text
	 */
	public static String getMapping(Client client, Class<?> indexClass) {
		return EsIndexerUtil.getElasticSearchIndexer(client, indexClass).getMapping(indexClass);
	}
	
	/**
	 * Delete a mapping type from index.
	 * 
	 * @param client
	 * @param indexClass - Mapping-type class
	 * @return The response {@link org.elasticsearch.action.admin.indices.mapping.delete.DeleteMappingResponse}
	 */
	public static DeleteMappingResponse deleteMapping(Client client, Class<?> indexClass) {
		return EsIndexerUtil.getElasticSearchIndexer(client, indexClass).deleteMapping(indexClass);
	}
}