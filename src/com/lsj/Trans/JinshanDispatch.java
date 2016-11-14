package com.lsj.Trans;

import com.lsj.Trans.Params.HttpPostParams;
import net.sf.json.JSONObject;

public class JinshanDispatch extends Dispatch {
	private static Dispatch unique = null;
	private JinshanDispatch(){
		this.base = "http://fy.iciba.com/ajax.php?a=fy";
		langMap.put("en", "en");
		langMap.put("zh", "zh");
	}
	
	public static JinshanDispatch Instance(){
		if(unique == null){
			unique = new JinshanDispatch();
		}
		return (JinshanDispatch)unique;
	}
	
	@Override
	public String Trans(String from, String targ, String query) throws Exception{
		
		HttpPostParams params = new HttpPostParams();
		
		params.put("f", langMap.get(from));
		params.put("t", langMap.get(targ));
		params.put("w", query);
		
		String jsonString = execute(params);
		return ParseJsonString(jsonString);
	}
	
	private String ParseJsonString(String jsonString){
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		return jsonObject.getJSONObject("content").getString("out");
	}
}
