package com.lsj.Trans;

import java.io.InputStream;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public abstract class Dispatch {
	protected String base;
	private CloseableHttpClient httpClient = HttpClients.createDefault();
	
	protected String execute(HttpParams form) throws Exception{
		
		HttpUriRequest request = form.RequestCreateByUrl(base);
		CloseableHttpResponse response = httpClient.execute(request);
		
		InputStream inputStream = response.getEntity().getContent();
		byte[] res = new byte[1024];
		int size = inputStream.read(res);
		String result = new String(res, 0, size);
		return result.replaceAll(" ", "");
	}
}
