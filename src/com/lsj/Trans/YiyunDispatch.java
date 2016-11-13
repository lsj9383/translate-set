package com.lsj.Trans;

public class YiyunDispatch extends Dispatch {
	public YiyunDispatch(){
		this.base = "http://www.yeecloud.com/translate.html";
	}
	
	public String Trans(String from, String targ, String query) throws Exception{
		HttpPostParams form = new HttpPostParams();
		
		form.put("text", query);
		form.put("srcl", "en");
		form.put("tgtl", "zh");
		
		return execute(form);
	}
}
