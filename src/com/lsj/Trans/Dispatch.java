package com.lsj.Trans;

import java.io.InputStream;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public abstract class Dispatch {
	protected String base;
	private CloseableHttpClient httpClient = HttpClients.createDefault();
	
	protected String execute(HttpForm form) throws Exception{
		HttpPost httpPost = new HttpPost(base);
		httpPost.setEntity(form.Entity());
		
		CloseableHttpResponse response = httpClient.execute(httpPost);
		
		InputStream inputStream = response.getEntity().getContent();
		byte[] res = new byte[1024];
		int size = inputStream.read(res);
		String result = new String(res, 0, size);
		return result.replaceAll(" ", "");
	}
}
