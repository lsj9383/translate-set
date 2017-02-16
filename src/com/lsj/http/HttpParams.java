package com.lsj.http;

public interface HttpParams {
	public String send(String baseUrl) throws Exception;
	public HttpParams put(String key, String value);
}
