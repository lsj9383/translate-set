package com.lsj.trans;

public interface Dispatch {
	public String trans(LANG from, LANG targ, String query) throws Exception;
}
