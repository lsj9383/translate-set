package com.lsj.trans.factory;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lsj.trans.AbstractOnlineTranslator;
import com.lsj.trans.Translator;
import com.lsj.trans.annotation.TranslatorComponent;
import com.lsj.trans.exception.DupIdException;
import com.lsj.trans.impl.BaiduTranslator;
import com.lsj.trans.impl.GoogleTranslator;
import com.lsj.trans.impl.JinshanTranslator;
import com.lsj.trans.impl.TencentTranslator;
import com.lsj.trans.impl.YoudaoTranslator;
import com.lsj.trans.impl.OmiTranslator;

public abstract class AbstractTranslatorFactory implements TFactory {
	
	protected Map<String, Translator> translatorMap = new HashMap<>();
	private List<String> workPackages = new ArrayList<>();

	private List<Class<? extends AbstractOnlineTranslator>> translatorClasses = Arrays.asList(
			BaiduTranslator.class,
			GoogleTranslator.class,
			JinshanTranslator.class,
			TencentTranslator.class,
			OmiTranslator.class,
			YoudaoTranslator.class);
			
	public AbstractTranslatorFactory() throws ClassNotFoundException, InstantiationException, IllegalAccessException, DupIdException, URISyntaxException{
		initSystemTranslator();
		initUserTranslator();
	}
	
	public AbstractTranslatorFactory(String[] workPackages) throws ClassNotFoundException, InstantiationException, IllegalAccessException, DupIdException, URISyntaxException{
		this();
	}
	
	public AbstractTranslatorFactory(String workPackage) throws ClassNotFoundException, InstantiationException, IllegalAccessException, DupIdException, URISyntaxException{
		this(new String[]{workPackage});
	}
	
	private void initSystemTranslator() throws ClassNotFoundException, InstantiationException, IllegalAccessException, DupIdException, URISyntaxException{
		for(Class<?> translatorClass : translatorClasses){
			TranslatorComponent component = translatorClass.getAnnotation(TranslatorComponent.class);
			if(component != null){
				String id = component.id();
				if(translatorMap.containsKey(id)){
					throw new DupIdException("Id duplication exception");
				}else{
					translatorMap.put(component.id(), (Translator) translatorClass.newInstance());	
				}
			}
		}
	}
	
	private void initUserTranslator() throws ClassNotFoundException, InstantiationException, IllegalAccessException, DupIdException, URISyntaxException{
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
	
	private List<String> getClassNameByPackage(String packageName) throws URISyntaxException{
		List<String> classesName = new ArrayList<>();
		ClassLoader loader = getClass().getClassLoader();
		URL url = loader.getResource(packageName.replace(".", "/"));

		File packageDir = new File(new URI(url.getPath()).getPath());
		for(File classFile : packageDir.listFiles()){
			String classNickName = classFile.getName();
			classNickName = classNickName.substring(0, classNickName.indexOf('.'));
			classesName.add(packageName+"."+classNickName);
		}
		return classesName;
	}
}
