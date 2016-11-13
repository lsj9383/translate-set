package com.lsj.Trans;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;

public class HttpGetParams implements HttpParams{
	private final Map<String, String> params = new HashMap<>();
	
	@Override
	public void put(String key, String value){
		params.put(key, value);
	}

	@Override
	public HttpUriRequest RequestCreateByUrl(String base) throws Exception {
		URIBuilder uri = new URIBuilder(base);
		for (String key : params.keySet()) {
			String value = params.get(key);
			uri.addParameter(key, value);
		}
		
		HttpGet request = new HttpGet(uri.toString());
		
		return request;
	}
}
