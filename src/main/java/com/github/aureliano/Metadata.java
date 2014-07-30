package com.github.aureliano;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Project metadata class model. This one is necessary to keep useful metadata
 * through execution. Its configuration is done by loading data from a properties file. It looks
 * for a file named <i>com/github/aureliano/metadata.properties</i>.
 */
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
	
	/**
	 * Single method. It ensures there's just one instance of this object.
	 * 
	 * @return The Metadata singleton.
	 */
	public static Metadata getInstance() {
		if (instance == null) {
			instance = new Metadata();
		}
		
		return instance;
	}
	
	protected void reset() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appName == null) ? 0 : appName.hashCode());
		result = prime * result + ((documentation == null) ? 0 : documentation.hashCode());
		result = prime * result + ((javaVersion == null) ? 0 : javaVersion.hashCode());
		result = prime * result + ((javadoc == null) ? 0 : javadoc.hashCode());
		result = prime * result + ((projectHome == null) ? 0 : projectHome.hashCode());
		result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
		result = prime * result + ((releaseVersion == null) ? 0 : releaseVersion.hashCode());
		result = prime * result + ((scmHome == null) ? 0 : scmHome.hashCode());
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
		Metadata other = (Metadata) obj;
		if (appName == null) {
			if (other.appName != null)
				return false;
		} else if (!appName.equals(other.appName))
			return false;
		if (documentation == null) {
			if (other.documentation != null)
				return false;
		} else if (!documentation.equals(other.documentation))
			return false;
		if (javaVersion == null) {
			if (other.javaVersion != null)
				return false;
		} else if (!javaVersion.equals(other.javaVersion))
			return false;
		if (javadoc == null) {
			if (other.javadoc != null)
				return false;
		} else if (!javadoc.equals(other.javadoc))
			return false;
		if (projectHome == null) {
			if (other.projectHome != null)
				return false;
		} else if (!projectHome.equals(other.projectHome))
			return false;
		if (releaseDate == null) {
			if (other.releaseDate != null)
				return false;
		} else if (!releaseDate.equals(other.releaseDate))
			return false;
		if (releaseVersion == null) {
			if (other.releaseVersion != null)
				return false;
		} else if (!releaseVersion.equals(other.releaseVersion))
			return false;
		if (scmHome == null) {
			if (other.scmHome != null)
				return false;
		} else if (!scmHome.equals(other.scmHome))
			return false;
		return true;
	}
}