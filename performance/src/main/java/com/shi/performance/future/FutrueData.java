package com.shi.performance.future;

public class FutrueData {

	protected RealData realData = null;// FutrueData 是RealData的包装
	protected boolean isReady = false;

	public synchronized void setRealData(RealData realData) {
		if (isReady) {
			return;
		}
		this.realData = realData;
		isReady = true;
		System.out.println("数据已经组装好，唤醒所有等待的线程--notifyall");
		notifyAll(); // RealData 已经被注入，通知getResult()
	}

	public FutrueData() {
	}

	public synchronized String getResult() { // 会等待RealData构造完成
		while (!isReady) {
			try {
				System.out.println("还没有数据，我还在等待   wait()");
				wait(); // 一直等待，直到RealData被注入
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return realData.result; // 由RealData实现
	}

}
