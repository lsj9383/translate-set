package com.lsj.Trans;

public class YouDaoDispatch extends Dispatch {
	public YouDaoDispatch(){
		this.base = "http://fanyi.youdao.com/translate?smartresult=dict&smartresult=rule&smartresult=ugc&sessionFrom=https://www.baidu.com/link";
	}
	
	public String Trans(String from, String targ, String query) throws Exception{
		
		HttpForm form = new HttpForm();
		
		form.put("type", "EN2ZH_CN");
		form.put("i", query);
		form.put("doctype", "json");
		form.put("xmlVersion", "1.8");
		form.put("keyfrom", "fanyi.web");
		form.put("ue", "UTF-8");
		form.put("action", "FY_BY_CLICKBUTTON");
		form.put("typoResult", "true");
		
		return execute(form);
	}
}
