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
```java
import com.lsj.Trans.Dispatch;
```

###2.获得翻译实体
有各种不同的翻译网站，每个网站对应一个类，并且每个网站类都是采用的单例模式。单例由Dispatch管理。
```java
Dispatch dispatch = Dispatch.Instance("google");
```

###3.翻译
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

#附录：包依赖
TranslateSet，依赖了两个第三方模块:HttpClient和Json。
* HttpClient jar
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
* JSON jar
	* commons-beanutils-1.8.0.jar
	* commons-collectioins-3.2.1.jar
	* commons-lang-2.5.jar
	* commons-logging-1.1.1.jar
	* ezmorph-1.0.6.jar
	* json-lib-2.5.jdk15.jdr

注意:HttpClient jar的版本建议使用最新，JSON jar应该严格按照上述版本进行配置。