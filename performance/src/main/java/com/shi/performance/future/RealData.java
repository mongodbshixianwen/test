package com.shi.performance.future;

import java.util.concurrent.Callable;

public class RealData implements Callable<String> {

	private String para;

	public RealData(String para) {
		this.para = para;
	}

	@Override
	public String call() throws Exception {
		// 这里是真实的业务逻辑，其执行可能很慢
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i <= 10; i++) {
			sb.append(para);

			Thread.sleep(100);
		}

		return sb.toString();
	}

	protected final String result;

//	public RealData(String queryStr) {
//		// RealData 的构建可能很慢，需要用户等很久，这里使用sleep模拟
//		StringBuffer sb = new StringBuffer();
//		for (int i = 0; i <= 10; i++) {
//			sb.append(queryStr);
//			// 循环中使用try catch 和 sleep 来制造一个很慢的过程
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//
//				e.printStackTrace();
//			}
//		}
//
//		result = sb.toString();
//	}

	public String getResult() {
		return result;
	}

}
