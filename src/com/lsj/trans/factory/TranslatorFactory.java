package com.lsj.trans.factory;

import java.net.URISyntaxException;

import com.lsj.trans.Translator;
import com.lsj.trans.exception.DupIdException;

final public class TranslatorFactory extends AbstractTranslatorFactory{

	public TranslatorFactory() throws ClassNotFoundException, InstantiationException, IllegalAccessException, DupIdException, URISyntaxException {
		super();
	}

	@Override
	public Translator get(String id) {
		return translatorMap.get(id);
	}

}
