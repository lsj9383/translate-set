package com.lsj.Trans.Params;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;

public class HttpPostParams extends HttpParams{
	
	@Override
	public void put(String key, String value){
		params.put(key, value);
	}

	@Override
	public HttpUriRequest RequestCreateByUrl(String base) throws Exception {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		for (String key : params.keySet()) {
			String value = params.get(key);
			formparams.add(new BasicNameValuePair(key, value));
		}
		HttpPost httpPost = new HttpPost(base);
		httpPost.setEntity(new UrlEncodedFormEntity(formparams, "UTF-8"));
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		return httpPost;
	}
}
