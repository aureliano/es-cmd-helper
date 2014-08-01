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

public class ElasticSearchCommandHelper {

	private static ElasticSearchCommandHelper instance;
	private Client clientElasticSearch;
	private ElasticSearchConfig configurationElasticSearch;
	
	private ElasticSearchCommandHelper() {
		super();
	}
	
	/**
	 * Single method. It ensures there's just one instance of this object.
	 * 
	 * @return The ElasticSearchCommandHelper singleton.
	 */
	public static ElasticSearchCommandHelper getInstance() {
		if (instance == null) {
			instance = new ElasticSearchCommandHelper();
		}
		
		return instance;
	}
	
	public ElasticSearchCommandHelper withElasticSearchConfiguration(ElasticSearchConfig configuration) {
		this.configurationElasticSearch = configuration;
		return this;
	}
	
	public ElasticSearchConfig elasticSearchConfiguration() {
		return this.configurationElasticSearch;
	}
	
	public boolean indexExist(Class<?> mappingClass) {
		this.startElasticSearchClient();
		boolean result = EsIndexDataStructure.indexExist(this.clientElasticSearch, mappingClass);
		if (this.configurationElasticSearch.isAutomaticClientClose()) {
			this.shutdownElasticSearchClient();
		}
		
		return result;
	}
	
	public boolean indexExist(String indexName) {
		this.startElasticSearchClient();
		boolean result = EsIndexDataStructure.indexExist(this.clientElasticSearch, indexName);
		if (this.configurationElasticSearch.isAutomaticClientClose()) {
			this.shutdownElasticSearchClient();
		}
		
		return result;
	}
	
	public boolean createIndex(Class<?> mappingClass) {
		this.startElasticSearchClient();
		CreateIndexResponse response = EsIndexDataStructure.createIndex(this.clientElasticSearch, mappingClass);
		if (this.configurationElasticSearch.isAutomaticClientClose()) {
			this.shutdownElasticSearchClient();
		}
		
		return (response != null) ? response.isAcknowledged() : false;
	}
	
	public boolean createIndex(String indexName) {
		this.startElasticSearchClient();
		CreateIndexResponse response = EsIndexDataStructure.createIndex(this.clientElasticSearch, indexName);
		if (this.configurationElasticSearch.isAutomaticClientClose()) {
			this.shutdownElasticSearchClient();
		}
		
		return (response != null) ? response.isAcknowledged() : false;
	}
	
	public boolean deleteIndex(Class<?> mappingClass) {
		this.startElasticSearchClient();
		DeleteIndexResponse response = EsIndexDataStructure.deleteIndex(this.clientElasticSearch, mappingClass);
		if (this.configurationElasticSearch.isAutomaticClientClose()) {
			this.shutdownElasticSearchClient();
		}
		
		return (response != null) ? response.isAcknowledged() : false;
	}
	
	public boolean deleteIndex(String indexName) {
		this.startElasticSearchClient();
		DeleteIndexResponse response = EsIndexDataStructure.deleteIndex(this.clientElasticSearch, indexName);
		if (this.configurationElasticSearch.isAutomaticClientClose()) {
			this.shutdownElasticSearchClient();
		}
		
		return (response != null) ? response.isAcknowledged() : false;
	}
	
	public boolean createMapping(Class<?> mappingClass) {
		this.startElasticSearchClient();
		if (!EsIndexDataStructure.indexExist(this.clientElasticSearch, mappingClass)) {
			ESIndex annotation = mappingClass.getAnnotation(ESIndex.class);
			String message = "Index %s does not exist. You must create it before you create a type.";
			message = message.replaceFirst("%s", (annotation == null ? "" : annotation.name()));
			
			throw new RuntimeException(message);
		}
		
		PutMappingResponse response = EsIndexDataStructure.createMapping(this.clientElasticSearch, mappingClass);
		if (this.configurationElasticSearch.isAutomaticClientClose()) {
			this.shutdownElasticSearchClient();
		}
		
		return (response != null) ? response.isAcknowledged() : false;
	}
	
	public String getMapping(Class<?> mappingClass) {
		this.startElasticSearchClient();
		String json = EsIndexDataStructure.getMapping(this.clientElasticSearch, mappingClass);
		if (this.configurationElasticSearch.isAutomaticClientClose()) {
			this.shutdownElasticSearchClient();
		}
		
		return json;
	}
	
	public boolean deleteMapping(Class<?> mappingClass) {
		this.startElasticSearchClient();
		if (!EsIndexDataStructure.indexExist(this.clientElasticSearch, mappingClass)) {
			ESIndex annotation = mappingClass.getAnnotation(ESIndex.class);
			String message = "Index %s does not exist. You must create it before you create a type.";
			message = message.replaceFirst("%s", (annotation == null ? "" : annotation.name()));
			
			throw new RuntimeException(message);
		}
		
		DeleteMappingResponse response = EsIndexDataStructure.deleteMapping(this.clientElasticSearch, mappingClass);
		if (this.configurationElasticSearch.isAutomaticClientClose()) {
			this.shutdownElasticSearchClient();
		}
		
		return (response != null) ? response.isAcknowledged() : false;
	}
	
	public void startElasticSearchClient() {
		if (this.clientElasticSearch == null) {
			this.clientElasticSearch= this.configureNewClient();
		}
	}
	
	public void shutdownElasticSearchClient() {
		this.clientElasticSearch.close();
		this.clientElasticSearch = null;
	}
	
	private Client configureNewClient() {
		if (this.configurationElasticSearch == null) {
			this.configurationElasticSearch = ElasticSearchConfig.loadConfigurationFromFile();
		}
		
		return new TransportClient().addTransportAddress(
			new InetSocketTransportAddress(
				this.configurationElasticSearch.getElasticSearchHost(),
				this.configurationElasticSearch.getTransportClientPort()
			)
		);
	}
}