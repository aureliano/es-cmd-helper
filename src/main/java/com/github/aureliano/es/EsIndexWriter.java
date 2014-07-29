package com.github.aureliano.es;

import java.lang.annotation.Annotation;

import org.elasticsearch.client.Client;
import org.elasticsearch.node.NodeBuilder;

import com.github.kzwang.osem.annotations.Indexable;
import com.github.kzwang.osem.api.ElasticSearchIndexer;
import com.github.kzwang.osem.impl.ElasticSearchIndexerImpl;

public class EsIndexWriter {

	private Client client;
	private static EsIndexWriter instance;
	
	private EsIndexWriter() {
		super();
		this.client = NodeBuilder.nodeBuilder().build().client();
	}
	
	public static EsIndexWriter getInstance() {
		if (instance == null) {
			instance = new EsIndexWriter();
		}
		
		return instance;
	}
	
	public void mapIndex(Class<?> indexClass) {
		Annotation indexable = indexClass.getAnnotation(Indexable.class);
		if (indexable == null) {
			throw new RuntimeException(indexClass.getCanonicalName() + " is not an indexable class");
		}
		
		String indexName = ((Indexable) indexable).name();
		ElasticSearchIndexer indexer = new ElasticSearchIndexerImpl(this.client, indexName);
		
		indexer.putMapping(indexClass, indexName);
	}
}