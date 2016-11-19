package com.lsj.trans.params;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.HttpUriRequest;

public abstract class HttpParams {
	protected final Map<String, String> params = new HashMap<>();
	abstract public HttpUriRequest RequestCreateByUrl(String base) throws Exception;
	abstract public void put(String key, String value);
}
