package com.yahoo.weather.framework;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.yahoo.weather.framework.enums.HTTP_METHOD;
import com.yahoo.weather.framework.enums.REST_FORMAT;


public class WebRequest {

	private String BASEURL = "http://query.yahooapis.com/v1/public";
	HTTP_METHOD method = HTTP_METHOD.GET;
	REST_FORMAT restFormat = REST_FORMAT.JSON;
	HashMap<String, String> headerInfo = new HashMap<String, String>();
	HashMap<String, String> paramInfo = new HashMap<String, String>();
	private int responseCode = 0;

	public void DefaultWebRequest() {
		URL url= null;
		try {
			url = new URL(BASEURL);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		headerInfo.put("Content-Type", "application/json");
		headerInfo.put("Accept-Encoding", "gzip,deflate");
		headerInfo.put("Host", url.getHost());
		headerInfo.put("Connection", "Keep-Alive");
		headerInfo.put("User-Agent", "QA");
	}


	public String send(IRequest reqObj) {
		String res = null;
		try {
			if(method == HTTP_METHOD.GET){
				res = sendGET(reqObj);
			} else if(method == HTTP_METHOD.POST){

			} else {

			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}

	public HTTP_METHOD getMethod() {
		return method;
	}

	public void setMethod(HTTP_METHOD method) {
		this.method = method;
	}
	
	public void setResponseCode(int respCode){
		this.responseCode = respCode;
	}
	
	public int getResponseCode(){
		return this.responseCode;
	}

	public REST_FORMAT getRestFormat() {
		return restFormat;
	}

	public void setRestFormat(REST_FORMAT restFormat) {
		this.restFormat = restFormat;
	}

	public void addHeader(String key , String val)
	{
		headerInfo.put(key, val);
	}

	public HashMap<String, String> getHeaders()
	{
		return this.headerInfo;
	}

	public void addParam(String key , String val)
	{
		paramInfo.put(key, val);
	}

	public HashMap<String, String> getParams()
	{
		return this.paramInfo;
	}

	public String getStringifiedParams()
	{
		StringBuilder sb = new StringBuilder();
		if(!paramInfo.isEmpty()){
			Iterator iterator = paramInfo.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry mapEntry = (Entry) iterator.next();
				sb.append(mapEntry.getKey()+ "=" + mapEntry.getValue() + "&");
			}
		} else {
			System.out.println("Parameters dont exist");
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	private String sendGET(IRequest reqObj){
		StringBuilder content = new StringBuilder();
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = null;
			System.out.println(BASEURL + reqObj.getResourceName());
			if(!paramInfo.isEmpty()) {
				request = new HttpGet(BASEURL + reqObj.getResourceName()+"?"+ getStringifiedParams());
			} else {
				request = new HttpGet(BASEURL + reqObj.getResourceName());
			}
			HttpResponse response = client.execute(request);
			BufferedReader br = new BufferedReader(
					new InputStreamReader((response.getEntity().getContent())));
			String line;
			while (null != (line = br.readLine())) {
				content.append(line);
				System.out.println(line);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return content.toString();
	}

}

