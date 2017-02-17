package com.lsj.http;

import java.io.File;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;

public class HttpMimeParams extends AbstractHttpParams {
	
	@Override
	protected CloseableHttpResponse send(CloseableHttpClient httpClient, String baseUrl) throws Exception {
		//1)构建实体
		MultipartEntityBuilder entBuilder = MultipartEntityBuilder.create();		  
		for (String key : params.keySet()) {
			Object item = params.get(key);
			if(item instanceof File){
				File file = (File)item;
				if((!file.exists()) || (file.isDirectory())){
					throw new Exception("file error");
				}
				entBuilder.addPart(key, new FileBody(file));				
			}else if(item instanceof String){
				String value = (String)item;
				entBuilder.addPart(key, new StringBody(value, ContentType.TEXT_PLAIN));
			}else{
				throw new Exception(item.getClass().toString()+" not support");
			}
		}
		HttpEntity reqEntity = entBuilder.build();
		
		//2)发送并等待回复
		HttpPost request = new HttpPost(baseUrl);
		request.setEntity(reqEntity);
		return httpClient.execute(request);
	}
}
