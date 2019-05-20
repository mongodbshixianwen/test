package com.shi.performance.future;

public class Client {

	public Data request(final String queryStr) {
		final FutrueData futrue = new FutrueData();

		// RealData 构建的很慢，所以在单独的线程中进行
		new Thread(() -> {
			RealData realData = new RealData(queryStr);
			futrue.setRealData(realData);
		}).start();
		return futrue;//futrueData会被立即
	}

}
