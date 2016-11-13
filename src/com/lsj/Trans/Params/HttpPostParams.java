package com.lsj.Trans.Params;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;

public class HttpPostParams implements HttpParams{
	private final List<NameValuePair> formparams = new ArrayList<NameValuePair>();
	
	@Override
	public void put(String key, String value){
		formparams.add(new BasicNameValuePair(key, value));
	}

	@Override
	public HttpUriRequest RequestCreateByUrl(String base) throws Exception {
		HttpPost httpPost = new HttpPost(base);
		httpPost.setEntity(new UrlEncodedFormEntity(formparams, "UTF-8"));
		return httpPost;
	}
}
