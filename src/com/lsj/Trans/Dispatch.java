package com.lsj.Trans;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.lsj.Trans.Params.HttpParams;

public abstract class Dispatch {
	protected Map<String, String> langMap = new HashMap<>();
	protected String base;
	private CloseableHttpClient httpClient = HttpClients.createDefault();
	
	protected String execute(HttpParams params) throws Exception{
		
		HttpUriRequest request = params.RequestCreateByUrl(base);		//根据不同的参数情况，创建不同的request(get或post)
		CloseableHttpResponse response = httpClient.execute(request);
		
		String jsonString = InputStream2JsonString(response.getEntity().getContent());	//将输入流全部读取出来转换为标准json字符串，在该函数会关闭输入流
		
		return jsonString;
	}
	
	private String InputStream2JsonString(InputStream is) throws Exception{
        Scanner scanner = new Scanner(is);
        String jsonString = scanner.useDelimiter("\\A").next();
        
        String buf = jsonString.replaceAll(",,", ",\"NULL\",");
		while(!jsonString.equals(buf)){
			jsonString = buf;
			buf = jsonString.replaceAll(",,", ",\"NULL\",");
		}
		
		scanner.close();
        is.close();
		return jsonString;
	}
}
