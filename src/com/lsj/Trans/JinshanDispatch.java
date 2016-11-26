package com.lsj.trans;

import com.lsj.trans.params.HttpPostParams;
import net.sf.json.JSONObject;

public class JinshanDispatch extends Dispatch {
	
	static{
		JinshanDispatch dispatch = new JinshanDispatch();
		classMap.put("jinshan", dispatch);
		classMap.put("Jinshan", dispatch);
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
		return ParseString(jsonString);
	}
	
	@Override
	protected String ParseString(String jsonString){
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		return jsonObject.getJSONObject("content").getString("out");
	}
}
