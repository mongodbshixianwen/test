package com.shi.performance.threadpool;

/**
 * 任务对象
 * @author zhht
 *
 */
public class MyThread implements Runnable {

	protected String name;

	public MyThread(String name) {
		this.name = name;
	}

	public void run() {

		try {
			Thread.sleep(100); //代替具体功能的执行
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
