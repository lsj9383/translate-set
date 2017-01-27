package Demo;

import com.lsj.trans.Dispatch;

public class Main {
	
	private static void TransInit() throws Exception{
		Class.forName("com.lsj.trans.BaiduDispatch");
		Class.forName("com.lsj.trans.GoogleDispatch");
		Class.forName("com.lsj.trans.JinshanDispatch");
		Class.forName("com.lsj.trans.YoudaoDispatch");
	}
	
	public static void main(String[] args) throws Exception {
		
		TransInit();
		String origin = "Xamarin.Forms has several layouts and features for organizing content on screen.\n Each layout control is described below, as well as details on how to handle screen orientation changes:";
		System.out.println("金山 : " + Dispatch.Instance("jinshan").Trans("en", "zh", origin));
		System.out.println("有道 : " + Dispatch.Instance("youdao").Trans("en", "zh", origin));
		System.out.println("百度 : " + Dispatch.Instance("baidu").Trans("en", "zh", origin));
		System.out.println("谷歌 : " + Dispatch.Instance("google").Trans("en", "zh", origin));
		System.out.println("\n");
		
	
		origin = "这个巡展，大家想去的先注册，临近当地巡展时候，会有注册确认邮件发给大家，届时可以看到详细的活动地址。\n等你工作了你就知道你当时的观念是多么的幼稚";
		System.out.println("金山 : " + Dispatch.Instance("jinshan").Trans("zh", "en", origin));
		System.out.println("有道 : " + Dispatch.Instance("youdao").Trans("zh", "en", origin));
		System.out.println("百度 : " + Dispatch.Instance("baidu").Trans("zh", "en", origin));
		System.out.println("谷歌 : " + Dispatch.Instance("google").Trans("zh", "en", origin));
		
//		System.out.println(Dispatch.Instance("google").Trans("ru", "zh", "Внатуре, спасибо тебе, братан. Короче, приходил сюда один такой сталкер, ну... мы с ним ничего не делали, честно. Он спустился к нам на базу к трубам вниз, так тут откуда-то кровосос взялся, да напал на наших людей, уволок двоих туда в логово двоих, включая его. Братана моего покоцало, я его сюда вытащил, кровь никак не остановить. Если ищешь его, спускайся вниз, только будь осторожен, опасайся той твари."));
	}

}
