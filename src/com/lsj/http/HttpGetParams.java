package com.lsj.http;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpGetParams extends AbstractHttpParams{

	@Override
	public String send(String baseUrl) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		URIBuilder uri = new URIBuilder(baseUrl);
		for (String key : params.keySet()) {
			String value = params.get(key);
			uri.addParameter(key, value);
		}
		HttpUriRequest request = new HttpGet(uri.toString());
		CloseableHttpResponse response = httpClient.execute(request);
		return readInputStream(response.getEntity().getContent());
	}
}
