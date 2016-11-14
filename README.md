#TranslateSet
一种小型的翻译集合工具。[这里]()是一个简单示例。考虑引入反射，进一步降低耦合。

#一、开始
###1.引入包
```java
import com.lsj.Trans.Dispatch;
import com.lsj.Trans.BaiduDispatch;
import com.lsj.Trans.GoogleDispatch;
import com.lsj.Trans.JinshanDispatch;
import com.lsj.Trans.YoudaoDispatch;
```

###2.获得翻译实体
有各种不同的翻译网站，每个网站对应一个类，并且全是单例模式。
```java
Dispatch dispatch = JinshanDispatch.Instance();
```

###3.翻译
```java
String zhResult = dispatch.Trans("en", "zh", "Learn Git and GitHub without any code!");		//英文翻译为中文
String enResult = dispatch.Trans("zh", "en", "希拉里败选后大哭");							//中文翻译为英文
```

#附录、包依赖:
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