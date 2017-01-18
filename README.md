#TranslateSet
一种小型的翻译集合工具。[这里](https://github.com/lsj9383/TranslateSet/blob/master/src/Demo/Main.java) 是一个使用示例。已利用该工具提供了[在线翻译](http://139.199.209.106/trans/) 工具。
目前仅支持**汉英互译**，支持的翻译来源:
* Google翻译
* 百度翻译
* 有道翻译
* 金山翻译

#一、*快速开始*
将仓库的lib目录下的所有jar添加到classpath中。并确保ide的编码方式是**utf-8**。
###1.*最小的实例*
```java
import com.lsj.trans.Dispatch;
public class Main {
	public static void main(String[] args) throws Exception {
		Class.forName("com.lsj.trans.GoogleDispatch");		
		System.out.println(Dispatch.Instance("google").Trans("en", "zh", "hello world"));
	}
```

###2.*引入包*
仅仅需要一个翻译转发的类，需要注意的是，这是一个抽象类不可实例化。
```java
import com.lsj.Trans.Dispatch;
```

###3.*加载需要的类*
在实际进行翻译之前，需要加载用到的转发类。
```java
Class.forName("com.lsj.trans.BaiduDispatch");		//加载百度的翻译转发类
Class.forName("com.lsj.trans.GoogleDispatch");		//加载Google的翻译转发类
Class.forName("com.lsj.trans.JinshanDispatch");		//加载金山的翻译转发类
Class.forName("com.lsj.trans.YoudaoDispatch");		//加载有道的翻译转发类
```

###4.*获得翻译实体*
有各种不同的翻译网站，每个网站对应一个类，并且每个网站类都是采用的单例模式。单例由Dispatch管理。当然获取翻译实体必须要事先**加载**，若没有加载则无法得到翻译实体(返回`null`)。
```java
Dispatch dispatch = Dispatch.Instance("google");
```

###5.*翻译*
```java
String zhResult = dispatch.Trans("en", "zh", "Learn Git and GitHub without any code!");		//英文翻译为中文
String enResult = dispatch.Trans("zh", "en", "希拉里败选后大哭");							//中文翻译为英文
```

#二、*API*
该工具提供的API相当简单:<br>
###1.*获得翻译实体*
```JAVA
dispatch = Dispatch.Instance("google");
dispatch = Dispatch.Instance("Google");

dispatch = Dispatch.Instance("baidu");
dispatch = Dispatch.Instance("Baidu");

dispatch = Dispatch.Instance("youdao");
dispatch = Dispatch.Instance("Youdao");

dispatch = Dispatch.Instance("jinshan");
dispatch = Dispatch.Instance("Jinshan");
```
###2.*翻译*
```JAVA
/*
 *langOri 和 langTag 用于指定中英文
 *中文 "zh"
 *英文 "en"
 *
 */
dispatch.Trans(langOri, langTag, string);
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