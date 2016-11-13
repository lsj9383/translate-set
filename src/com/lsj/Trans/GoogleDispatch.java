package com.lsj.Trans;

public class GoogleDispatch extends Dispatch {
	public GoogleDispatch(){
		this.base = "http://translate.google.cn/translate_a/single";
	}
	
	public String Trans(String from, String targ, String query) throws Exception{
		HttpGetParams params = new HttpGetParams();
		
		params.put("client", "t");
		params.put("sl", "en");
		params.put("tl", "zh-CN");
		params.put("hl", "zh-CN");
		params.put("dt", "at");
		params.put("dt", "bd");
		params.put("dt", "ex");
		params.put("dt", "ld");
		params.put("dt", "md");
		params.put("dt", "qca");
		params.put("dt", "rw");
		params.put("dt", "rm");
		params.put("dt", "ss");
		params.put("dt", "t");
		
		params.put("ie", "UTF-8");
		params.put("oe", "UTF-8");
		params.put("source", "btn");
		params.put("srcrom", "1");
		params.put("ssel", "0");
		params.put("tsel", "0");
		params.put("kc", "11");
		params.put("tk", "338590.203232");
		params.put("q", query);
		
		return execute(params);
	}
}