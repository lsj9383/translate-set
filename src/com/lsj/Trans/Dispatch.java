package com.lsj.trans;

import java.util.HashMap;
import java.util.Map;

public abstract class Dispatch {
	protected static Map<String, Dispatch> classMap = new HashMap<>();			//类名映射，由子类完成
	protected Map<String, String> langMap = new HashMap<>();					//语言映射，由子类完成
	
	abstract public String Trans(String from, String targ, String query) throws Exception;
	
	static public Dispatch Instance(String name) throws Exception{
		return classMap.get(name);
	}
}
