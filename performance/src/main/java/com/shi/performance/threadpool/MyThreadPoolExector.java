package com.shi.performance.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolExector extends ThreadPoolExecutor {

	public MyThreadPoolExector(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		System.out.println("beforeExecute");
		System.out.println("beforeExecute:"+t.getName()+t.getId());
		 
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {

		System.out.println("afterExecute:"+this.getPoolSize());
	}
}
