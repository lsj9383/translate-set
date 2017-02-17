package com.lsj.trans.impl;

import com.lsj.http.HttpParams;
import com.lsj.http.HttpPostParams;
import com.lsj.trans.AbstractOnlineTranslator;
import com.lsj.trans.LANG;
import com.lsj.trans.annotation.TranslatorComponent;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@TranslatorComponent(id = "tencent")
final public class TencentTranslator extends AbstractOnlineTranslator {

	public TencentTranslator(){
		langMap.put(LANG.EN, "1");
		langMap.put(LANG.ZH, "0");
	}

	@Override
	protected String getResponse(LANG from, LANG targ, String query) throws Exception {
		HttpParams params = new HttpPostParams()
				.put("sl", langMap.get(from))
				.put("tl", langMap.get(targ))
				.put("st", query);

		return params.send2String("http://fanyi.qq.com/api/translate");
	}
	
	@Override
	protected String parseString(String jsonString){
		StringBuilder str = new StringBuilder();
		JSONObject rootObj = JSONObject.fromObject(jsonString);
		JSONArray array = rootObj.getJSONArray("result");
		
		for(int i=0; i<array.size(); i++){
			str.append(array.getJSONObject(i).getString("dst"));
		}
		return str.toString();
	}

}
