package com.lsj.Trans;

import org.apache.http.client.methods.HttpUriRequest;

public interface HttpParams {
	public HttpUriRequest RequestCreateByUrl(String base) throws Exception;
	public void put(String key, String value);
}
