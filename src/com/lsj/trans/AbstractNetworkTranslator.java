package com.lsj.trans;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractNetworkTranslator implements Translator {
	protected static Map<String, AbstractNetworkTranslator> classMap = new HashMap<>();			//类名映射，由子类完成
	protected Map<LANG, String> langMap = new HashMap<>();					//语言映射，由子类完成
	
	@Override
	final public String trans(LANG from, LANG targ, String query) throws Exception {
		String result = parseString(getResponse(from, targ, query));
		return result;
	}
	
	static public AbstractNetworkTranslator getInstance(String name) throws Exception{
		return classMap.get(name);
	}
	
	abstract protected String getResponse(LANG from, LANG targ, String query) throws Exception ;
	abstract protected String parseString(String jsonString);
}
