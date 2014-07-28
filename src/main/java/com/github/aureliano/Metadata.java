package com.github.aureliano;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Metadata {

	private String appName;
	private String releaseVersion;
	private String releaseDate;
	private String javaVersion;
	private String projectHome;
	private String scmHome;
	private String documentation;
	private String javadoc;
	
	private static Metadata instance;
	
	private Metadata() {
		this.reset();
	}
	
	public static Metadata getInstance() {
		if (instance == null) {
			instance = new Metadata();
		}
		
		return instance;
	}
	
	private void reset() {
		Properties p = new Properties();
		InputStream stream = ClassLoader.getSystemResourceAsStream("com/github/aureliano/metadata.properties");
		
		try {
			p.load(stream);
			this.fillData(p);
		} catch (IOException ex) {
			throw new RuntimeException("Fail to load project metadata.", ex);
		}		
	}
	
	private void fillData(Properties p) {
		this.appName = p.getProperty("app.name");
		this.releaseVersion = p.getProperty("release.version");
		this.releaseDate = p.getProperty("release.date");
		this.javaVersion = p.getProperty("java.version");
		this.projectHome = p.getProperty("project.home");
		this.scmHome = p.getProperty("scm.home");
		this.documentation = p.getProperty("documentation");
		this.javadoc = p.getProperty("javadoc");
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getReleaseVersion() {
		return releaseVersion;
	}

	public void setReleaseVersion(String releaseVersion) {
		this.releaseVersion = releaseVersion;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getJavaVersion() {
		return javaVersion;
	}

	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}

	public String getProjectHome() {
		return projectHome;
	}

	public void setProjectHome(String projectHome) {
		this.projectHome = projectHome;
	}

	public String getScmHome() {
		return scmHome;
	}

	public void setScmHome(String scmHome) {
		this.scmHome = scmHome;
	}

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

	public String getJavadoc() {
		return javadoc;
	}

	public void setJavadoc(String javadoc) {
		this.javadoc = javadoc;
	}	
}