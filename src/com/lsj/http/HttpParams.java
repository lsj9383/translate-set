package com.lsj.http;

public interface HttpParams {
	public String Send(String baseUrl) throws Exception;
	public HttpParams put(String key, String value);
}
