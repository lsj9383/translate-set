package com.lsj.trans;

import com.lsj.http.HttpParams;
import com.lsj.http.HttpPostParams;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TencentDispatch extends AbstractDispatch {
	
	static{
		TencentDispatch dispatch = new TencentDispatch();
		classMap.put("tencent", dispatch);
		classMap.put("Tencent", dispatch);
	}
	
	private TencentDispatch(){
		langMap.put(LANG.EN, "1");
		langMap.put(LANG.ZH, "0");
	}

	@Override
	protected String getResponse(LANG from, LANG targ, String query) throws Exception {
		HttpParams params = new HttpPostParams()
				.put("sl", langMap.get(from))
				.put("tl", langMap.get(targ))
				.put("st", query);

		return params.send("http://fanyi.qq.com/api/translate");
	}
	
	@Override
	protected String parseString(String jsonString){
		StringBuilder str = new StringBuilder();
		JSONObject rootObj = JSONObject.fromObject(jsonString);
		JSONArray array = rootObj.getJSONArray("result");
		
		for(int i=0; i<array.size(); i++){
			str.append(array.getJSONObject(i).getString("dst"));
		}
		return str.toString();
	}

}
