package com.yahoo.weather.framework;

public interface IResponse {
	public String parseJSON(String json);
	public String parseXML(String xml);
	
}
