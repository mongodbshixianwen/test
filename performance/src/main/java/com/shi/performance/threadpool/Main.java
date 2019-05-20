package com.shi.performance.threadpool;

public class Main {

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			ThreadPool.getInstance().start(new MyThread("testThreadPool" + Integer.toString(i)));
		}
		long useTime = System.currentTimeMillis() - start;

		System.out.println(useTime);
	}
}
