package com.lsj.Trans;

import com.lsj.Trans.Params.HttpPostParams;
import net.sf.json.JSONObject;

public class BaiduDispatch extends Dispatch {
	
	static{
		String ClassName = "com.lsj.Trans.BaiduDispatch";
		ClassMap.put("baidu", ClassName);
		ClassMap.put("Baidu", ClassName);
	}
	
	public BaiduDispatch(){
		this.base = "http://fanyi.baidu.com/v2transapi";
		langMap.put("en", "en");
		langMap.put("zh", "zh");
	}
	
	@Override
	public String Trans(String from, String targ, String query) throws Exception{
		
		HttpPostParams params = new HttpPostParams();
		
		params.put("from", langMap.get(from));
		params.put("to", langMap.get(targ));
		params.put("query", query);
		params.put("transtype", "translang");
		params.put("simple_means_flag", "3");
		
		String jsonString = execute(params);
		
		return ParseJsonString(jsonString);
	}
	
	private String ParseJsonString(String jsonString){
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		return jsonObject.getJSONObject("trans_result").getJSONArray("data").getJSONObject(0).getString("dst");
	}
}
