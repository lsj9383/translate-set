package com.lsj.trans;

import com.lsj.trans.params.HttpPostParams;
import net.sf.json.JSONObject;

public class BaiduDispatch extends Dispatch {
	
	static{
		String ClassName = BaiduDispatch.class.getName();
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
		
		params = new HttpPostParams();
		
		params.put("from", langMap.get(from));
		params.put("to", langMap.get(targ));
		params.put("query", query);
		params.put("transtype", "translang");
		params.put("simple_means_flag", "3");
		
		String jsonString = execute();
		
		return ParseJsonString(jsonString);
	}
	
	@Override
	protected String ParseJsonString(String jsonString){
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		return jsonObject.getJSONObject("trans_result").getJSONArray("data").getJSONObject(0).getString("dst");
	}
}
