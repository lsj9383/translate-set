package com.lsj.Trans;

public class BaiduDispatch extends Dispatch {
	public BaiduDispatch(){
		this.base = "http://fanyi.baidu.com/v2transapi";
	}
	
	public String Trans(String from, String targ, String query) throws Exception{
		
		HttpPostParams form = new HttpPostParams();
		
		form.put("from", "en");
		form.put("to", "zh");
		form.put("query", query);
		form.put("transtype", "translang");
		form.put("simple_means_flag", "3");
		
		return execute(form);
	}
}
