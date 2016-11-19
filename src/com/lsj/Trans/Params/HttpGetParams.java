package com.lsj.trans.params;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;

public class HttpGetParams extends HttpParams{
	
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
