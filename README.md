#TranslateSet
一种小型的翻译集合工具。[这里](https://github.com/lsj9383/TranslateSet/blob/master/src/Demo/Main.java)是一个简单示例。
目前支持的翻译来源:
* Google翻译
* 百度翻译
* 有道翻译
* 金山翻译(金山翻译的来源是百度)

#一、开始
需要的jar包都在仓库根目录的jar文件夹下。请注意，lib文件夹是编译工具工程所依赖的包，详细的请看**附录：包依赖**
###1.引入包
仅仅需要一个翻译转发的类，需要注意的是，这是一个抽象类不可实例化。
```java
import com.lsj.Trans.Dispatch;
```

###2.加载需要的类
在实际进行翻译之前，需要加载用到的转发类。
```java
Class.forName("com.lsj.trans.BaiduDispatch");		//加载百度的翻译转发类
Class.forName("com.lsj.trans.GoogleDispatch");		//加载Google的翻译转发类
Class.forName("com.lsj.trans.JinshanDispatch");		//加载金山的翻译转发类
Class.forName("com.lsj.trans.YoudaoDispatch");		//加载有道的翻译转发类
```

###3.获得翻译实体
有各种不同的翻译网站，每个网站对应一个类，并且每个网站类都是采用的单例模式。单例由Dispatch管理。当然获取翻译实体必须要事先**加载**，若没有加载则无法得到翻译实体(返回`null`)。
```java
Dispatch dispatch = Dispatch.Instance("google");
```

###4.翻译
```java
String zhResult = dispatch.Trans("en", "zh", "Learn Git and GitHub without any code!");		//英文翻译为中文
String enResult = dispatch.Trans("zh", "en", "希拉里败选后大哭");							//中文翻译为英文
```

#二、API
该工具提供的API相当简单:<br>
###1.获得翻译实体
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
###2.翻译
```JAVA
/*
 *langOri 和 langTag 用于指定中英文
 *中文 "zh"
 *英文 "en"
 *
 */
dispatch.Trans(langOri, langTag, string);
```

#三、扩展
只要用户知道翻译所需要发送的http请求的详细信息以及返回数据的解析方式，那么用户就可以通过继承Dispatch类和使用HttpParams类来完成自己的翻译实体类。作自行扩展主要需要知道以下类:
###1.HttpParams
用来添加需要上传的数据,POST请求或GET请求中的数据将会保存在该类中。需要引起注意的是这是个抽象类不能直接实例化，这是因为不同的请求方式其细节是不同的，该类已经将细节封装了起来并由其子类实现。
####1).初始化
当前支持有限，只支持两种子类实现。
```java
PostParams = new HttpPostParams();	//用来添加并保存Post的数据
GetParams = new HttpGetParams();	//用来添加并保存Get的数据
```
####2).添加数据
都只使用了最简单的情况，以key-value的方式将数据进行保存。需要注意的是, put方法是会返回对象本身的，因此可以通过链式方式进行数据的添加，这样代码更为美观，更少冗余。
```java
Params.put("from", langMap.get(from))
		.put("to", langMap.get(targ))
		.put("query", query)
		.put("transtype", "translang")
		.put("simple_means_flag", "3");
```
####3).创建请求对象
为了更方便的控制，这里提供了根据具体的请求方式创建对应的请求对象的接口，该请求对象是可以直接发出请求的，也可以客户对请求进行二次加工，例如添加cookie或者请求头。
```java
HttpUriRequest request = params.RequestCreateByUrl(base);
CloseableHttpResponse response = httpClient.execute(request);
String responseContent = response.getEntity().getContent());
```
###2.Dispatch
HttpParams抽象类只是个工具类，可以单独抽取出来使用，为了更方便我们的开发，提供了Dispatch抽象类，我们开发新类都应该继承于该抽象类。除此外还需关注一下几点
####1).对静态初始化函数的实现
翻译属于服务类，不同的Dispatch服务类只需要一个对象即可，即需要实现单例类。为了进一步减少代码冗余，单例的管理已经全部放在了Dispatch抽象类中了。我们只需要在程序里面讲程序进行单例的注册即可。只有经过注册的类才能收到Dispatch的管理。
```java
package com.lsj.dispatch;

public class MyDispatch extends Dispatch {
	static {
		Dispatch instance = new MyDispatch();
		classMap.put("myDispatch", instance);
	}
}
```
如上述所示，在static将当前类的实例放入classMap中，这样通过`Dispatch.Instance("myDispatch")`方法就能取出放入的实例。需要注意的是，由于static是在类加载的时候才调用，客户端进行类加载`Class.forName("com.lsj.dispatch.MyDispatch");`，这样才会调用`MyDispatch类`的static构造函数。
####2).翻译方法
Dispatch中有一个接口是进行具体的翻译服务，在该接口方法实现中，需要使用`params`对象，这个对象是在Dispatch中声明的HttpParams对象。当对象将需要传递的参数添加好后，就调用Dispatch的execute方法，该方法将会自动提取params中的请求进行发送。
```java
@Override
public String Trans(String from, String targ, String query) throws Exception{
	
	params = new HttpPostParams()
			.put(key, value);
	
	String jsonString = execute();
	
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

#附录：jar清单
* HttpClient jar(第三方jar)
	* commons-codec-1.9.jar
	* commons-logging-1.2.jar
	* fluent-hc-4.5.2.jar
	* httpclient-4.5.2.jar
	* httpclient-cache-4.5.2.jar
	* httpclient-win-4.5.2.jar
	* httpcore-4.4.4.jar
	* httpmime-4.5.2.jar
	* jna-4.1.0.jar
	* jna-platform-4.1.0.jar
* JSON jar(第三方jar)
	* commons-beanutils-1.8.0.jar
	* commons-collectioins-3.2.1.jar
	* commons-lang-2.5.jar
	* commons-logging-1.1.1.jar
	* ezmorph-1.0.6.jar
	* json-lib-2.5.jdk15.jdr
* until_trans.jar(核心jar)

注意:HttpClient jar的版本建议使用最新，JSON jar应该严格按照上述版本进行配置。