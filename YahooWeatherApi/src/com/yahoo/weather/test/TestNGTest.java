package com.yahoo.weather.test;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.yahoo.weather.framework.WebRequest;
import com.yahoo.weather.framework.enums.HTTP_METHOD;
import com.yahoo.weather.framework.enums.REST_FORMAT;
import com.yahoo.weather.rest.Weather;

public class TestNGTest {

	@BeforeTest
	public void getReady(){
		System.out.println("Getting Ready");
	}

	@Test
	public void testAdd() {
		WebRequest wr = new WebRequest();
		Weather w = new Weather();
		wr.setMethod(HTTP_METHOD.GET);
		wr.setRestFormat(REST_FORMAT.JSON);
		wr.send(w);
		assertEquals(wr.getResponseCode(), 200);
	}

	@AfterTest
	public void closeApi(){
		System.out.println("Closing All Requests");
	}
}
