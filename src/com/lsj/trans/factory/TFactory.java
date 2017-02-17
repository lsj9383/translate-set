package com.lsj.trans.factory;

import com.lsj.trans.Translator;

public interface TFactory {
	Translator get(String id);
}
