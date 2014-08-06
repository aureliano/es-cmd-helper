package com.github.aureliano.data;

public class DownloadConfiguration {

	private String serverHost;
	private Integer serverPort;
	private String url;
	private String user;
	private String password;
	
	public DownloadConfiguration() {
		super();
	}
	
	public String getServerHost() {
		return serverHost;
	}
	
	public DownloadConfiguration withServerHost(String serverHost) {
		this.serverHost = serverHost;
		return this;
	}
	
	public Integer getServerPort() {
		return serverPort;
	}
	
	public DownloadConfiguration withServerPort(Integer serverPort) {
		this.serverPort = serverPort;
		return this;
	}
	
	public String getUrl() {
		return url;
	}
	
	public DownloadConfiguration withUrl(String url) {
		this.url = url;
		return this;
	}
	
	public String getUser() {
		return user;
	}
	
	public DownloadConfiguration withUser(String user) {
		this.user = user;
		return this;
	}
	
	public String getPassword() {
		return password;
	}
	
	public DownloadConfiguration withPassword(String password) {
		this.password = password;
		return this;
	}
}