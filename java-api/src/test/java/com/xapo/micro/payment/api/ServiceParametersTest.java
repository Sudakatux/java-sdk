package com.xapo.micro.payment.api;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ServiceParametersTest {

	@Test
	public void testConstructor() {
		String serviceURL = "http://dev.xapo.com:8089/pay_button/show";
		ServiceParameters serviceParameters = new ServiceParameters(serviceURL);

		assertEquals("Scheme", "http", serviceParameters.getScheme());
		assertEquals("Host", "dev.xapo.com", serviceParameters.getHost());
		assertEquals("Path", "/pay_button/show", serviceParameters.getPath());
		assertEquals("Port", 8089, serviceParameters.getPort());

	}

	@Test
	public void testConstructor2() {
		String serviceURL = "https://www.xapo.com/";
		ServiceParameters serviceParameters = new ServiceParameters(serviceURL);

		assertEquals("Scheme", "https", serviceParameters.getScheme());
		assertEquals("Host", "www.xapo.com", serviceParameters.getHost());
		assertEquals("Path", "/", serviceParameters.getPath());
		assertEquals("Port", -1, serviceParameters.getPort());
	}

	@Test
	public void testConstructor3() {
		String serviceURL = "https://xapo.com";
		ServiceParameters serviceParameters = new ServiceParameters(serviceURL);

		assertEquals("Scheme", "https", serviceParameters.getScheme());
		assertEquals("Host", "xapo.com", serviceParameters.getHost());
		assertEquals("Path", "/", serviceParameters.getPath());
		assertEquals("Port", -1, serviceParameters.getPort());

	}

	
}
