package com.lsj.trans;

public interface Translator {
	public String trans(LANG from, LANG targ, String query) throws Exception;
}
