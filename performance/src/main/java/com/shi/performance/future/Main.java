package com.shi.performance.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Main {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		FutureTask<String> future = new FutureTask<>(new RealData("a"));
		
		RealData d = new RealData("a");
		ExecutorService exec = Executors.newFixedThreadPool(1);
		Future a = exec.submit(d);
//		exec.execute(d);
		System.out.println("请求完毕");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		System.out.println("数据=" + a.get());

		// Client client = new Client();
		// // 这里会立即返回，因为得到的是FutrueData而不是RealData
		// Data data = client.request("name");
		// System.out.println("请求完毕");
		// System.out.println("立即返回数据="+data.getResult());
		//
		// try {
		// //这里用sleep代替其它业务逻辑
		// //在处理这些业务逻辑时RealData被创建，从而充分利用了等待的时间
		// Thread.sleep(2000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		//
		// //使用真实的数据
		// System.out.println("异步返回的 数据="+data.getResult());
	}

}
