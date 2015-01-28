package com.yahoo.weather.rest;

import com.yahoo.weather.framework.IRequest;
import com.yahoo.weather.framework.IResponse;

public class Weather implements IRequest, IResponse {

	@Override
	public String parseJSON(String json) {
		return json;
		// TODO Verification can be done here
		
	}

	@Override
	public String parseXML(String xml) {
		return xml;
		// TODO Verification can be done here
		
	}

	@Override
	public String getResourceName() {
		return "/yql?q=select%20*%20from%20geo.places%20where%20text=%22india,%20in%22&format=json";
	}
	
	

}
