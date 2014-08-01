package com.github.aureliano;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * ElasticSearch configuration class model. This one is necessary to keep useful configuration
 * through execution.
 */
public class ElasticSearchConfig {
	
	private String elasticSearchHost;
	private int transportClientPort;
	private boolean automaticClientClose;

	private static final Logger logger = Logger.getLogger(ElasticSearchConfig.class);
	private static final String DEFAULT_ELASTIC_SEARCH_HOST = "127.0.0.1";
	private static final int DEFAULT_TRANSPORT_CLIENT_PORT = 9300;
	private static final boolean DEFAULT_AUTOMATIC_CLIENT_CLOSE = true;
	
	public static final String ELASTIC_SEARCH_PROPERTIES_FILE = "elasticsearch.properties";
	
	public ElasticSearchConfig() {
		this.elasticSearchHost = DEFAULT_ELASTIC_SEARCH_HOST;
		this.transportClientPort = DEFAULT_TRANSPORT_CLIENT_PORT;
		this.automaticClientClose = DEFAULT_AUTOMATIC_CLIENT_CLOSE;
	}
	
	/**
	 * Load ElasticSearch configuration from elasticsearch.properties file. This method
	 * looks for a file name elasticsearch.properties in the root path of the
	 * application (./elasticsearch.properties).
	 * 
	 * @return ElasticSearchConfig - configuration from ./elasticsearch.properties
	 */
	public static ElasticSearchConfig loadConfigurationFromFile() {
		Properties p = new Properties();
		
		try {
			InputStream stream = new FileInputStream(ELASTIC_SEARCH_PROPERTIES_FILE);
			p.load(stream);
			return fillData(p);
		} catch (FileNotFoundException ex) {
			throw new RuntimeException("Could not find ElasticSearch configuration file ./" + ELASTIC_SEARCH_PROPERTIES_FILE);
		} catch (IOException ex) {
			logger.error(ex.getMessage(), ex);
			throw new RuntimeException("Fail to load ElasticSearch configuration.", ex);
		}
	}
	
	private static ElasticSearchConfig fillData(Properties p) {
		ElasticSearchConfig configuration = new ElasticSearchConfig();
		if (p.getProperty("elastic.search.host") != null) {
			configuration.withElasticSearchHost(p.getProperty("elastic.search.host"));
		}		
		
		if (p.getProperty("transport.client.port") != null) {
			configuration.withTransportClientPort(Integer.parseInt(p.getProperty("transport.client.port")));
		}
		
		if (p.getProperty("automatic.client.close") != null) {
			configuration.withAutomaticClientClose(Boolean.parseBoolean("automatic.client.close"));
		}
		
		return configuration;
	}

	/**
	 * Get ElasticSearch host. Default value is "127.0.0.1".
	 * 
	 * @return The ElasticSearch host.
	 */
	public String getElasticSearchHost() {
		return elasticSearchHost;
	}

	/**
	 * Set ElasticSearch host.
	 * 
	 * @param host - The ElasticSearch host.
	 * @return ElasticSearchConfig - this object
	 */
	public ElasticSearchConfig withElasticSearchHost(String host) {
		this.elasticSearchHost = host;
		return this;
	}

	/**
	 * Get transport client port to be set on {@link org.elasticsearch.common.transport.InetSocketTransportAddress}.
	 * 
	 * @return The port number.
	 */
	public int getTransportClientPort() {
		return transportClientPort;
	}

	/**
	 * Set transport client port to be set on {@link org.elasticsearch.common.transport.InetSocketTransportAddress}.
	 * 
	 * @param port - The port number.
	 * @return ElasticSearchConfig - this object
	 */
	public ElasticSearchConfig withTransportClientPort(int port) {
		this.transportClientPort = port;
		return this;
	}
	
	/**
	 * Get automatic ElasticSearch client close. If it is true after each command executed on ElasticSearch
	 * the client will be closed.
	 * 
	 * @return ElasticSearchConfig - this object
	 */
	public boolean isAutomaticClientClose() {
		return this.automaticClientClose;
	}
	
	/**
	 * Set automatic ElasticSearch client close. If it is true after each command executed on ElasticSearch
	 * the client will be closed.
	 * 
	 * @param automaticClientClose
	 * @return ElasticSearchConfig - this object
	 */
	public ElasticSearchConfig withAutomaticClientClose(boolean automaticClientClose) {
		this.automaticClientClose = automaticClientClose;
		return this;
	}
}