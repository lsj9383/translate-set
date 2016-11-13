package com.lsj.Trans;

import com.lsj.Trans.Params.HttpPostParams;
import net.sf.json.JSONObject;

public class BaiduDispatch extends Dispatch {
	public BaiduDispatch(){
		this.base = "http://fanyi.baidu.com/v2transapi";
	}
	
	public String Trans(String from, String targ, String query) throws Exception{
		
		HttpPostParams params = new HttpPostParams();
		
		params.put("from", "en");
		params.put("to", "zh");
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
