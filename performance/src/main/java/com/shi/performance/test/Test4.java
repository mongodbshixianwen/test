package com.shi.performance.test;

import java.util.HashMap;
import java.util.Map;

public class Test4 {

	public static int i;

	public volatile Map<String, String> map = new HashMap<>();

	public static void main(String[] args) throws Exception {
		new Test4().t1();
	}

	public void t1() throws InterruptedException {
		Map<String, String> m1 = map;
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				map.put("key-" + i, "value-" + i);
			}
		}, "t1-thread");

		t1.start();
		Thread t2 = new Thread(() -> {

			m1.put("key-1", "key-999");
			map = m1;
			System.out.println("修改完成");

		}, "t2-thread");

		t2.start();
		// t2.sleep(1000);

		Thread t3 = new Thread(() -> {
			while (true) {
				if (map.size() > 0) {
					for (Map.Entry<String, String> m : map.entrySet()) {
						System.out.println(m.getKey() + ":" + m.getValue());
					}
				}
			}
		}, "t3-thread");

		t3.start();
	}
}
