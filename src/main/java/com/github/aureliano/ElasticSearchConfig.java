package com.github.aureliano;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * ElasticSearch configuration class model. This one is necessary to keep useful configuration
 * through execution. Its configuration is done by loading data from a properties file. It looks
 * for a file named <i>./elasticsearch.properties</i>.
 */
public class ElasticSearchConfig {
	
	private String elasticSearchHost;
	private int transportClientPort;

	private static ElasticSearchConfig instance;
	private static final Logger logger = Logger.getLogger(ElasticSearchConfig.class);
	
	private ElasticSearchConfig() {
		this.reset();
	}
	
	/**
	 * Single method. It ensures there's just one instance of this object.
	 * 
	 * @return The ElasticSearchConfig singleton.
	 */
	public static ElasticSearchConfig getInstance() {
		if (instance == null) {
			instance = new ElasticSearchConfig();
		}
		
		return instance;
	}
	
	protected void reset() {
		Properties p = new Properties();
		String configFile = "elasticsearch.properties";
		InputStream stream = ClassLoader.getSystemResourceAsStream(configFile);
		
		if (stream == null) {
			throw new RuntimeException("Could not find ElasticSearch configuration file ./" + configFile);
		}
		
		try {
			p.load(stream);
			this.fillData(p);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw new RuntimeException("Fail to load ElasticSearch configuration.", ex);
		}		
	}
	
	private void fillData(Properties p) {
		this.elasticSearchHost = p.getProperty("elastic.search.host");
		if (p.getProperty("transport.client.port") != null) {
			this.transportClientPort = Integer.parseInt(p.getProperty("transport.client.port"));
		} else {
			this.transportClientPort = 9300;
		}
	}

	public String getElasticSearchHost() {
		return elasticSearchHost;
	}

	public void setElasticSearchHost(String host) {
		this.elasticSearchHost = host;
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
	 */
	public void setTransportClientPort(int port) {
		this.transportClientPort = port;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((elasticSearchHost == null) ? 0 : elasticSearchHost.hashCode());
		result = prime * result + transportClientPort;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElasticSearchConfig other = (ElasticSearchConfig) obj;
		if (elasticSearchHost == null) {
			if (other.elasticSearchHost != null)
				return false;
		} else if (!elasticSearchHost.equals(other.elasticSearchHost))
			return false;
		if (transportClientPort != other.transportClientPort)
			return false;
		return true;
	}
}