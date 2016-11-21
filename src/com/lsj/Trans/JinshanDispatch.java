package com.lsj.trans;

import com.lsj.trans.params.HttpPostParams;
import net.sf.json.JSONObject;

public class JinshanDispatch extends Dispatch {
	
	static{
		String ClassName = JinshanDispatch.class.getName();
		classMap.put("jinshan", ClassName);
		classMap.put("Jinshan", ClassName);
	}
	
	public JinshanDispatch(){
		this.base = "http://fy.iciba.com/ajax.php?a=fy";
		langMap.put("en", "en");
		langMap.put("zh", "zh");
	}
	
	@Override
	public String Trans(String from, String targ, String query) throws Exception{
		params = new HttpPostParams()
				.put("f", langMap.get(from))
				.put("t", langMap.get(targ))
				.put("w", query);
		
		String jsonString = execute();
		return ParseJsonString(jsonString);
	}
	
	@Override
	protected String ParseJsonString(String jsonString){
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		return jsonObject.getJSONObject("content").getString("out");
	}
}
