package com.lsj.trans;

import com.lsj.http.HttpParams;
import com.lsj.http.HttpPostParams;

import net.sf.json.JSONObject;

public class JinshanDispatch extends Dispatch {
	
	static{
		JinshanDispatch dispatch = new JinshanDispatch();
		classMap.put("jinshan", dispatch);
		classMap.put("Jinshan", dispatch);
	}
	
	private JinshanDispatch(){
		langMap.put("en", "en");
		langMap.put("zh", "zh");
	}
	
	@Override
	public String Trans(String from, String targ, String query) throws Exception{
		HttpParams params = new HttpPostParams()
				.put("f", langMap.get(from))
				.put("t", langMap.get(targ))
				.put("w", query);
		
		String jsonString = params.Send("http://fy.iciba.com/ajax.php?a=fy");
	
		return ParseString(jsonString);
	}
	
	
	private String ParseString(String jsonString){
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		return jsonObject.getJSONObject("content").getString("out");
	}
}
