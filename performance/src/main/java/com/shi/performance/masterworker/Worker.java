package com.shi.performance.masterworker;

import java.util.Map;
import java.util.Queue;

public class Worker implements Runnable {

	// 任务队列，用于取得子任务
	protected Queue<Object> workQueue;
	// 子任务，用于处理结果集
	protected Map<String, Object> resultMap;

	public void setWorkerQueue(Queue<Object> workQueue) {
		this.workQueue = workQueue;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	@Override
	public void run() {
		while (true) {
			// 获取子任务
			Object input = workQueue.poll();
			if (input == null)
				break;
			// 处理子任务
			Object re = handle(input);
			// 将处理结果写入结果集
			resultMap.put(Integer.toString(input.hashCode()), re);
		}

	}

	// 子任务处理逻辑，在子类中实现具体逻辑
	public  Object handle(Object input) {
		return input;
	}

}
