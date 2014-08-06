package com.github.aureliano.data;

import junit.framework.Assert;

import org.junit.Test;

public class DownloaderTest {
	
	private DownloadConfiguration configuration() {
		return new DownloadConfiguration()
			.withServerHost("localhost")
			.withServerPort(1)
			.withUrl("www.test.com")
			.withUser("user")
			.withPassword("pass");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateAuthHttpsConfigurationNull() {
		try {
			Downloader.validateAuthHttpsConfiguration(null);
		} catch (IllegalArgumentException ex) {
			Assert.assertEquals("Download configuration cannot be null", ex.getMessage());
			throw ex;
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateAuthHttpsConfigurationServerHostNull() {
		try {
			Downloader.validateAuthHttpsConfiguration(this.configuration().withServerHost(null));
		} catch (IllegalArgumentException ex) {
			Assert.assertEquals("Server host cannot be empty", ex.getMessage());
			throw ex;
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateAuthHttpsConfigurationServerHostEmpty() {
		try {
			Downloader.validateAuthHttpsConfiguration(this.configuration().withServerHost(""));
		} catch (IllegalArgumentException ex) {
			Assert.assertEquals("Server host cannot be empty", ex.getMessage());
			throw ex;
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateAuthHttpsConfigurationServerPortNull() {
		try {
			Downloader.validateAuthHttpsConfiguration(this.configuration().withServerPort(null));
		} catch (IllegalArgumentException ex) {
			Assert.assertEquals("Server port cannot be null", ex.getMessage());
			throw ex;
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateAuthHttpsConfigurationServerPortInvalid() {
		try {
			Downloader.validateAuthHttpsConfiguration(this.configuration().withServerPort(0));
		} catch (IllegalArgumentException ex) {
			Assert.assertEquals("Server port must be higher than 0", ex.getMessage());
			throw ex;
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateAuthHttpsConfigurationUrlNull() {
		try {
			Downloader.validateAuthHttpsConfiguration(this.configuration().withUrl(null));
		} catch (IllegalArgumentException ex) {
			Assert.assertEquals("URL cannot be empty", ex.getMessage());
			throw ex;
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateAuthHttpsConfigurationUrlEmpty() {
		try {
			Downloader.validateAuthHttpsConfiguration(this.configuration().withUrl(""));
		} catch (IllegalArgumentException ex) {
			Assert.assertEquals("URL cannot be empty", ex.getMessage());
			throw ex;
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateAuthHttpsConfigurationUserNull() {
		try {
			Downloader.validateAuthHttpsConfiguration(this.configuration().withUser(null));
		} catch (IllegalArgumentException ex) {
			Assert.assertEquals("User cannot be empty", ex.getMessage());
			throw ex;
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateAuthHttpsConfigurationUserEmpty() {
		try {
			Downloader.validateAuthHttpsConfiguration(this.configuration().withUser(""));
		} catch (IllegalArgumentException ex) {
			Assert.assertEquals("User cannot be empty", ex.getMessage());
			throw ex;
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateAuthHttpsConfigurationPasswordNull() {
		try {
			Downloader.validateAuthHttpsConfiguration(this.configuration().withPassword(null));
		} catch (IllegalArgumentException ex) {
			Assert.assertEquals("Password cannot be empty", ex.getMessage());
			throw ex;
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateAuthHttpsConfigurationPasswordEmpty() {
		try {
			Downloader.validateAuthHttpsConfiguration(this.configuration().withPassword(""));
		} catch (IllegalArgumentException ex) {
			Assert.assertEquals("Password cannot be empty", ex.getMessage());
			throw ex;
		}
	}
}