package com.lsj.http;

import java.io.InputStream;

public interface HttpParams {
	public String send2String(String baseUrl) throws Exception;
	public InputStream send2InputStream(String baseUrl) throws Exception;
	public HttpParams put(String key, String value);
}
