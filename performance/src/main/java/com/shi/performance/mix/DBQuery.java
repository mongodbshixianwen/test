package com.shi.performance.mix;

public class DBQuery implements IDbquery {

	public DBQuery(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String request() {
		 
		return "request String";
	}
}
