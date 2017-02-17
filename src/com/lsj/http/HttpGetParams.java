package com.lsj.http;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

public class HttpGetParams extends AbstractHttpParams{

	@Override
	protected CloseableHttpResponse send(CloseableHttpClient httpClient, String baseUrl) throws Exception {
		URIBuilder uri = new URIBuilder(baseUrl);
		for (String key : params.keySet()) {
			String value = params.get(key);
			uri.addParameter(key, value);
		}
		HttpUriRequest request = new HttpGet(uri.toString());
		return httpClient.execute(request);
	}
}
