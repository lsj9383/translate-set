package Demo;

import com.lsj.Trans.BaiduDispatch;
import com.lsj.Trans.GoogleDispatch;
import com.lsj.Trans.JinshanDispatch;
import com.lsj.Trans.YoudaoDispatch;

public class Main {

	public static void main(String[] args) throws Exception {
		
		String origin = "An HTTP message can contain a number of headers describing properties of the message such as the content length, content type and so on. HttpClient provides methods to retrieve, add, remove and enumerate headers.";
		
		System.out.println("金山 : " + JinshanDispatch.Instance().Trans("en", "zh", origin));
		System.out.println("有道 : " + YoudaoDispatch.Instance().Trans("en", "zh", origin));
		System.out.println("百度 : " + BaiduDispatch.Instance().Trans("en", "zh", origin));
		System.out.println("谷歌 : " + GoogleDispatch.Instance().Trans("en", "zh", origin));
		System.out.println("\n");
		
		
		origin = "这个巡展，大家想去的先注册，临近当地巡展时候，会有注册确认邮件发给大家，届时可以看到详细的活动地址";
		System.out.println("金山 : " + JinshanDispatch.Instance().Trans("zh", "en", origin));
		System.out.println("有道 : " + YoudaoDispatch.Instance().Trans("zh", "en", origin));
		System.out.println("百度 : " + BaiduDispatch.Instance().Trans("zh", "en", origin));
		System.out.println("谷歌 : " + GoogleDispatch.Instance().Trans("zh", "en", origin));
	}

}
