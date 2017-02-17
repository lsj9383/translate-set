#TranslateSet
一种小型的翻译集合工具，[这里](https://github.com/lsj9383/translate-set/blob/master/src/test/TransTest.java) 是一个使用示例。并且提供[在线翻译](http://139.199.209.106/trans/) 。
目前仅支持**汉英互译**，支持的翻译来源:
* [谷歌翻译](http://translate.google.cn/)
* [百度翻译](http://fanyi.baidu.com/)
* [腾讯翻译](http://fanyi.qq.com/)
* [有道翻译](http://fanyi.youdao.com/)
* [金山翻译](http://fy.iciba.com/)

#一、*快速开始*
将仓库的lib目录下的所有jar添加到classpath中。并确保ide的编码方式是**【UTF-8】**。
###1.*最小的实例*
```java
import com.lsj.trans.LANG;
import com.lsj.trans.factory.TFactory;
import com.lsj.trans.factory.TranslatorFactory;

public class Main {
	public static void main(String[] args) throws Exception {
		TFactory factory = new TranslatorFactory();
		System.out.println(factory.get("google").trans(LANG.EN, LANG.ZH, "hello world"));
	}
}
```

###2.*引入包*
引入所需要的package。
```java
import com.lsj.trans.LANG;							/* 翻译的语言支持 */
import com.lsj.trans.factory.TFactory;				/* 工厂接口 */
import com.lsj.trans.factory.TranslatorFactory;		/* 翻译工厂 */
```

###3.*实例化翻译工厂*
在实际进行翻译之前，需要初始化翻译工厂，该工厂提供不同类型的翻译实例。
```java
TFactory factory = new TranslatorFactory();
```

###4.*获得翻译实体*
有各种不同的翻译实例，不同的实例有不同的翻译能力。由工厂管理。工厂在初始化的时候会将用注解标注了的翻译类加载到工厂缓存中，工厂相同的情况，使用相同id获得的翻译实例是完全相同的，相当于单例模式。
```java
Translator lator = factory.get("google");
```

###5.*翻译*
```java
String zhResult = lator.trans(LANG.EN, LANG.ZH, "Learn Git and GitHub without any code!");		//英文翻译为中文
String enResult = lator.trans(LANG.ZH, LANG.EN, "希拉里败选后大哭");							//中文翻译为英文
```

#二、*API*
该工具提供的API相当简单:<br>
###1.*获得翻译实体*
```JAVA
lator = factory.get("google");
lator = factory.get("baidu");
lator = factory.get("youdao");
lator = factory.get("jinshan");
lator = factory.get("tencent");
```
###2.*翻译*
```JAVA
/*
 *fromLang 和 toLang 用于指定源语言 与 目标翻译语言
 *中文 LANG.ZH
 *英文 LANG.EN
 *
 */
lator.trans(LANG fromLang, LANG toLang, string);
```

#三、*扩展*
扩展这里介绍如何使用本项目提供的构架扩展翻译源。只要用户知道翻译所需要发送的http请求的详细信息以及返回数据的解析方式，那么用户就可以通过继承Dispatch类和使用HttpParams类来完成自己的翻译实体类。作自行扩展主要需要知道以下类:
###1.*HttpParams*
这是一个接口，它提供两个方法`put()`和`Send()`，分布用于添加待发送的数据以及进行发送。
####1).*初始化*
当前支持有限，只支持两种子类实现。
```java
HttpParams postParams = new HttpPostParams();	//用来添加并保存Post的数据
HttpParams getParams = new HttpGetParams();		//用来添加并保存Get的数据
HttpParams mimeParams = new HttpMimeParams();	//用来添加并保存Multipart/form数据
```
####2).*添加数据*
都只使用了最简单的情况，以key-value的方式将数据进行保存。需要注意的是, put方法是会返回对象本身的，因此可以通过链式方式进行数据的添加，这样代码更为美观，更少冗余。
```java
Params.put("from", langMap.get(from))
		.put("to", langMap.get(targ))
		.put("query", query)
		.put("transtype", "translang")
		.put("simple_means_flag", "3");
```
####3).*发送请求*
请求的数据添加好后，就可以调用Send()方法发送请求数据，并且得到一个String类型的返回数据。
```java
String baseUrl = "http://....";
Params.Send(baseUrl);
```
###2.*自定义翻译类*
HttpParams抽象类只是个工具类，可以单独抽取出来使用，为了更方便我们的开发，提供了Dispatch抽象类，我们开发新类都应该继承于该抽象类。除此外还需关注一下几点
####1).对静态初始化函数的实现
翻译属于服务类，不同的Dispatch服务类只需要一个对象即可。Dispatch类中管理了classMap对象，这个对象是个HashMap，用于缓存翻译对象。在自定义翻译类中，需要用静态代码块在classMap中缓存对象，这样便将翻译对象交给了Dispatch管理。由于静态代码块只会在类加载到jvm中的时候才会执行，这也是要通过Class.forName("com...")进行类加载的原因。
```java
package com.lsj.dispatch;

public class MyDispatch extends Dispatch {
	static {
		Dispatch instance = new MyDispatch();
		classMap.put("myDispatch", instance);
	}
}
```
如上述所示，在static将当前类的实例放入classMap中，这样通过`Dispatch.Instance("myDispatch")`方法就能取出放入的实例。需要注意的是，由于static是在类加载的时候才调用，客户端进行类加载`Class.forName("com.lsj.dispatch.MyDispatch");`这样才会调用`MyDispatch类`的static构造函数。
####2).翻译方法
在trans方法中，就进行具体的翻译工作。提供翻译在线api所需要的参数，再将其发送过去获取回应后再解析即可。
```java
@Override
public String Trans(String from, String targ, String query) throws Exception{
	
	String jsonString = new HttpPostParams()
							.put(key, value)
							.Send("http://...");
	
	return ParseJsonString(jsonString);
}
```
####3).数据解析
不同的翻译网站返回的结果的数据类型、数据结构都是不同的。例如有的数据类型是json，有的数据类型是xml，而数据结构更是大相径庭。为了适应这多变的情况，需要开发人员熟悉具体的数据类型和结构，自行做解析数据并提取出需要的内容。
```java
@Override
protected String ParseJsonString(String jsonString){
	JSONObject jsonObject = JSONObject.fromObject(jsonString);		//将json字符串转换为json对象
	....
	return ... ;
}
```

以上就是一个完整的Dispatch扩展的开发方式，[这里](https://github.com/lsj9383/TranslateSet/blob/master/src/com/lsj/Trans/JinshanDispatch.java) 可以参考一个Dispatch子类的实现。