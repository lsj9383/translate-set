package com.lsj.trans.factory;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lsj.trans.Translator;
import com.lsj.trans.annotation.TranslatorComponent;
import com.lsj.trans.exception.DupIdException;

public abstract class AbstractTranslatorFactory implements TFactory {
	
	protected Map<String, Translator> translatorMap = new HashMap<>();
	private List<String> workPackages = new ArrayList<>();
	
	public AbstractTranslatorFactory() throws ClassNotFoundException, InstantiationException, IllegalAccessException, DupIdException{
		workPackages.add("com.lsj.trans.impl");
		initTranslator();
	}
	
	public AbstractTranslatorFactory(String[] workPackages) throws ClassNotFoundException, InstantiationException, IllegalAccessException, DupIdException{
		this();
	}
	
	public AbstractTranslatorFactory(String workPackage) throws ClassNotFoundException, InstantiationException, IllegalAccessException, DupIdException{
		this(new String[]{workPackage});
	}
	
	private void initTranslator() throws ClassNotFoundException, InstantiationException, IllegalAccessException, DupIdException{
		for(String workPackage : workPackages){
			List<String> workClassesName = getClassNameByPackage(workPackage);
			for(String workClassName : workClassesName){
				Class<?> workClass = Class.forName(workClassName);
				TranslatorComponent component = workClass.getAnnotation(TranslatorComponent.class);
				if(component != null){
					String id = component.id();
					if(translatorMap.containsKey(id)){
						throw new DupIdException("Id duplication exception");
					}else{
						translatorMap.put(component.id(), (Translator) workClass.newInstance());	
					}
				}
			}
		}
	}
	
	private List<String> getClassNameByPackage(String packageName){
		List<String> classesName = new ArrayList<>();
		ClassLoader loader = getClass().getClassLoader();
		URL url = loader.getResource(packageName.replace(".", "/"));
		File packageDir = new File(url.getPath());
		for(File classFile : packageDir.listFiles()){
			String classNickName = classFile.getName();
			classNickName = classNickName.substring(0, classNickName.indexOf('.'));
			classesName.add(packageName+"."+classNickName);
		}
		return classesName;
	}
}
