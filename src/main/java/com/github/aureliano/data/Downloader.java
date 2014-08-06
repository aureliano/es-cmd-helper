package com.github.aureliano.data;

import java.io.IOException;
import java.security.cert.X509Certificate;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

@SuppressWarnings("deprecation")
public class Downloader {

	private static final Logger logger = Logger.getLogger(Downloader.class);
	
	private Downloader() {
		super();
	}
	
	public static String doHttpGet(DownloadConfiguration conf) {
		HttpGet httpGet = new HttpGet(conf.getUrl());
		logger.info("Downloading resource via HTTP Get method >>> " + conf.getUrl());
		
		return download(httpGet);
	}
	
	public static String doHttpPost(DownloadConfiguration conf) {
		HttpPost httpPost = new HttpPost(conf.getUrl());
		logger.info("Downloading resource via HTTP Post method >>> " + conf.getUrl());
		
		return download(httpPost);
	}
	
	public static String doAuthHttpsGet(DownloadConfiguration conf) {
		validateAuthHttpsConfiguration(conf);
		try {
			return download(conf.getServerHost(), conf.getServerPort(),
				new HttpGet(conf.getUrl()), conf.getUser(), conf.getPassword());
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public static String doAuthHttpsPost(DownloadConfiguration conf) {
		validateAuthHttpsConfiguration(conf);
		try {
			return download(conf.getServerHost(), conf.getServerPort(),
				new HttpPost(conf.getUrl()), conf.getUser(), conf.getPassword());
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	private static String download(HttpRequestBase request) {		
		ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

            public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
        		int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					return entity != null ? EntityUtils.toString(entity) : null;
				} else {
					HttpEntity entity = response.getEntity();
					logger.warn(entity != null ? EntityUtils.toString(entity) : null);
					
					throw new ClientProtocolException("Unexpected response status: " + status);
				}
			}
		};
		
		try {
			return HttpClients.createDefault().execute(request, responseHandler);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	private static String download(String serverHost, int serverPort,
			HttpRequestBase request, String user, String password) throws Exception {
		
		TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
			@Override
			public boolean isTrusted(X509Certificate[] certificate, String authType) {
				return true;
			}
		};

		SSLSocketFactory sf = new SSLSocketFactory(acceptingTrustStrategy, 
		SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		SchemeRegistry registry = new SchemeRegistry();
		registry.register(new Scheme("https", serverPort, sf));
		ClientConnectionManager ccm = new PoolingClientConnectionManager(registry);
			 
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(
		new AuthScope(serverHost, serverPort),
		new UsernamePasswordCredentials(user, password));
				
		DefaultHttpClient httpClient = new DefaultHttpClient(ccm);
		httpClient.setCredentialsProvider(credsProvider);
		
		HttpResponse response = httpClient.execute(request);
		
		return EntityUtils.toString(response.getEntity());
	}
	
	protected static void validateAuthHttpsConfiguration(DownloadConfiguration conf) {
		if (conf == null) {
			throw new IllegalArgumentException("Download configuration cannot be null");
		}
		
		validateServerHost(conf.getServerHost());
		validateServerPort(conf.getServerPort());
		validateUrl(conf.getUrl());
		validateUser(conf.getUser());
		validatePassword(conf.getPassword());
	}
	
	private static void validateServerHost(String host) {
		if (host == null ||  host.equals("")) {
			throw new IllegalArgumentException("Server host cannot be empty");
		}
	}
	
	private static void validateServerPort(Integer port) {
		if (port == null) {
			throw new IllegalArgumentException("Server port cannot be null");
		} else if (port <= 0) {
			throw new IllegalArgumentException("Server port must be higher than 0");
		}
	}
	
	private static void validateUrl(String url) {
		if (url == null ||  url.equals("")) {
			throw new IllegalArgumentException("URL cannot be empty");
		}
	}
	
	private static void validateUser(String user) {
		if (user == null ||  user.equals("")) {
			throw new IllegalArgumentException("User cannot be empty");
		}
	}
	
	private static void validatePassword(String password) {
		if (password == null ||  password.equals("")) {
			throw new IllegalArgumentException("Password cannot be empty");
		}
	}
}