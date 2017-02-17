package com.lsj.trans.factory;

import com.lsj.trans.Translator;
import com.lsj.trans.exception.DupIdException;

final public class TFactory extends AbstractTranslatorFactory{

	public TFactory() throws ClassNotFoundException, InstantiationException, IllegalAccessException, DupIdException {
		super();
	}

	@Override
	public Translator get(String id) {
		return translatorMap.get(id);
	}

}
