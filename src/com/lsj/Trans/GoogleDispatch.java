package com.lsj.Trans;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class GoogleDispatch extends Dispatch {
	private static ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
	
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
		params.put("tk", tk(query));
		params.put("q", query);
		
		return execute(params);
	}
	
	private String tk(String val) throws Exception{
		String script ="function tk(a) {"
						+"var TKK = ((function() {var a = 561666268;var b = 1526272306;return 406398 + '.' + (a + b); })());\n"
						+"function b(a, b) { for (var d = 0; d < b.length - 2; d += 3) { var c = b.charAt(d + 2), c = 'a' <= c ? c.charCodeAt(0) - 87 : Number(c), c = '+' == b.charAt(d + 1) ? a >>> c : a << c; a = '+' == b.charAt(d) ? a + c & 4294967295 : a ^ c } return a }\n"
						+"for (var e = TKK.split('.'), h = Number(e[0]) || 0, g = [], d = 0, f = 0; f < a.length; f++) {"  
						+"var c = a.charCodeAt(f);"  
						+"128 > c ? g[d++] = c : (2048 > c ? g[d++] = c >> 6 | 192 : (55296 == (c & 64512) && f + 1 < a.length && 56320 == (a.charCodeAt(f + 1) & 64512) ? (c = 65536 + ((c & 1023) << 10) + (a.charCodeAt(++f) & 1023), g[d++] = c >> 18 | 240, g[d++] = c >> 12 & 63 | 128) : g[d++] = c >> 12 | 224, g[d++] = c >> 6 & 63 | 128), g[d++] = c & 63 | 128)"  
						+"}"      
						+"a = h;"  
						+"for (d = 0; d < g.length; d++) a += g[d], a = b(a, '+-a^+6');"  
						+"a = b(a, '+-3^+b+-f');"  
						+"a ^= Number(e[1]) || 0;"  
						+"0 > a && (a = (a & 2147483647) + 2147483648);"  
						+"a %= 1E6;"  
						+"return a.toString() + '.' + (a ^ h)\n"
						+"}";
		
		engine.eval(script);  
		Invocable inv = (Invocable) engine;
		return (String) inv.invokeFunction("tk", val);
	}
}