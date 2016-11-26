package com.lsj.trans;

import com.lsj.trans.params.HttpPostParams;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BaiduDispatch extends Dispatch {
	
	static{
		BaiduDispatch dispatch = new BaiduDispatch();
		classMap.put("baidu", dispatch);
		classMap.put("Baidu", dispatch);
	}
	
	public BaiduDispatch(){
		this.base = "http://fanyi.baidu.com/v2transapi";
		langMap.put("en", "en");
		langMap.put("zh", "zh");
	}
	
	@Override
	public String Trans(String from, String targ, String query) throws Exception{
		
		params = new HttpPostParams()
				.put("from", langMap.get(from))
				.put("to", langMap.get(targ))
				.put("query", query)
				.put("transtype", "translang")
				.put("simple_means_flag", "3");
		
		String jsonString = execute();
		
		return ParseJsonString(jsonString);
	}
	
	@Override
	protected String ParseJsonString(String jsonString){
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		JSONArray segments = jsonObject.getJSONObject("trans_result").getJSONArray("data");
		StringBuilder result = new StringBuilder();
		
		for(int i=0; i<segments.size(); i++){
			result.append(i==0?"":"\n");
			result.append(segments.getJSONObject(i).getString("dst"));
		}
		return new String(result);
	}
}
