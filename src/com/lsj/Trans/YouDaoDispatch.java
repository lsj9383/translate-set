package com.lsj.Trans;

import com.lsj.Trans.Params.HttpPostParams;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class YouDaoDispatch extends Dispatch {
	public YouDaoDispatch(){
		this.base = "http://fanyi.youdao.com/translate?smartresult=dict&smartresult=rule&smartresult=ugc&sessionFrom=https://www.baidu.com/link";
	}
	
	public String Trans(String from, String targ, String query) throws Exception{
		
		HttpPostParams params = new HttpPostParams();
		
		params.put("type", "EN2ZH_CN");
		params.put("i", query);
		params.put("doctype", "json");
		params.put("xmlVersion", "1.8");
		params.put("keyfrom", "fanyi.web");
		params.put("ue", "UTF-8");
		params.put("action", "FY_BY_CLICKBUTTON");
		params.put("typoResult", "true");
		
		String jsonString = execute(params);
		return ParseJsonString(jsonString);
	}
	
	private String ParseJsonString(String jsonString){
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		JSONArray parts = jsonObject.getJSONArray("translateResult").getJSONArray(0);
		String result = new String();
		
		for(int i=0; i<parts.size(); i++){
			JSONObject item = parts.getJSONObject(i);
			result += item.getString("tgt");
		}
		
		return result;
	}
}
