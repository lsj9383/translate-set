package com.lsj.trans.factory;

import com.lsj.trans.Translator;

public interface TranslatorFactory {
	Translator get(String id);
}
