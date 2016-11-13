package Demo;

import com.lsj.Trans.YouDaoDispatch;

public class Main {

	public static void main(String[] args) throws Exception {
		YouDaoDispatch dispatch = new YouDaoDispatch();
		
		System.out.println(dispatch.Trans(null, null, "i am king"));
		System.out.println(dispatch.Trans(null, null, "i am king"));
	}

}
