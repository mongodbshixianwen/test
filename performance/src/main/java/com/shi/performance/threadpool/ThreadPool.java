package com.shi.performance.threadpool;

import java.util.List;
import java.util.Vector;

/**
 * 线程的创建和关闭由线程池维护，线程通常不会因为执行完一次任务而被关闭，线程池中的线程会被多个任务重复使用
 * 为了节省系统在多线程并发时不断创建和销毁线程所带来的额外开销，就需要引入了线程池，基本功能是线程复用
 * 需要一个线程时，并不着急立即去创建线程，而是先去线程池查找是否有空余的线程，若有则直接使用线程池中的线程工作，
 * 若没有，在去创建新的线程，待任务完成后，也不简单的销毁线程，而是将线程放入线程池的空闲队列，等待下次使用
 * 
 * @author zhht
 */
public class ThreadPool {

	private static ThreadPool instance = null; // 私有静态实例

	private List<PThread> idleThreads;// 线程池的队列// 空闲的线程队列

	private int threadCounter;// 已有的线程总数

	private boolean isShutdown = false; // 线程池是否停止

	private ThreadPool() { // 构造函数，初始化实例...
		this.idleThreads = new Vector(5);
		threadCounter = 0;
	}

	public int getCreateThreadsCount() {
		return threadCounter;
	}

	// 取得线程池的实例
	public synchronized static ThreadPool getInstance() {
		if (instance == null) {
			instance = new ThreadPool();
		}
		return instance;
	}

	// 将线程放入池中
	protected synchronized void repool(PThread repoolingThread) {
		if (!isShutdown) {
			idleThreads.add(repoolingThread);
		} else {
			repoolingThread.shutDown();
		}
	}

	// 停止池中所有的线程
	public synchronized void shutDown() {

		isShutdown = true;
		for (int threadIndex = 0; threadIndex < idleThreads.size(); threadIndex++) {
			PThread idleThread = (PThread) idleThreads.get(threadIndex);
			idleThread.shutDown();
		}
	}

	// 执行任务 线程池执行一个任务的函数
	public synchronized void start(Runnable target) {
		PThread thread = null;
		// 如果有空闲线程则直接使用
		if (idleThreads.size() > 0) {
			int lastIndex = idleThreads.size() - 1;
			thread = (PThread) idleThreads.get(lastIndex);
			idleThreads.remove(lastIndex);
			// 立即执行这个任务
			thread.setTarget(target);
		} else {// 没有闲置的线程，则创建新线程
			threadCounter++;
			// 创建新线程
			thread = new PThread(target, "PThread #" + threadCounter, this);
			// 启动这个线程
			thread.start();

		}

	}

}
