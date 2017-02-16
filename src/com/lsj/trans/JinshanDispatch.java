package com.lsj.trans;

import com.lsj.http.HttpParams;
import com.lsj.http.HttpPostParams;

import net.sf.json.JSONObject;

public class JinshanDispatch extends AbstractDispatch {
	
	static{
		JinshanDispatch dispatch = new JinshanDispatch();
		classMap.put("jinshan", dispatch);
		classMap.put("Jinshan", dispatch);
	}
	
	private JinshanDispatch(){
		langMap.put(LANG.EN, "en");
		langMap.put(LANG.ZH, "zh");
	}
	
	@Override
	protected String getResponse(LANG from, LANG targ, String query) throws Exception{
		HttpParams params = new HttpPostParams()
				.put("f", langMap.get(from))
				.put("t", langMap.get(targ))
				.put("w", query);
		
		return params.send("http://fy.iciba.com/ajax.php?a=fy");
	}
	
	
	@Override
	protected String parseString(String jsonString){
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		String result = jsonObject.getJSONObject("content").getString("out");
		return result;
	}
}
