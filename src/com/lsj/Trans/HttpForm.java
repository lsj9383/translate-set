package com.lsj.Trans;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

public class HttpForm {
	private final List<NameValuePair> formparams = new ArrayList<NameValuePair>();
	
	void put(String key, String value){
		formparams.add(new BasicNameValuePair(key, value));
	}
	
	UrlEncodedFormEntity Entity() throws Exception{
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
		return entity;
	}
}
