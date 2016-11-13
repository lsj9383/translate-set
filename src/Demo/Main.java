package Demo;

import com.lsj.Trans.BaiduDispatch;
import com.lsj.Trans.GoogleDispatch;
import com.lsj.Trans.YouDaoDispatch;

public class Main {

	public static void main(String[] args) throws Exception {
		
		String origin = "An HTTP message can contain a number of headers describing properties of the message such as the content length, content type and so on. HttpClient provides methods to retrieve, add, remove and enumerate headers.";
		System.out.println("有道 : " + new YouDaoDispatch().Trans(null, null, origin));
		System.out.println("百度 : " + new BaiduDispatch().Trans(null, null, origin));
		System.out.println("谷歌 : " + new GoogleDispatch().Trans(null, null, origin));

	}

}
