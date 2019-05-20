package com.shi.performance.test;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.zhht.das.BerthBook;
import com.zhht.utils.PlateUtils;

public class Test {

	public static void main(String[] args) throws Exception {

		BerthBook berthBook = new BerthBook();
		berthBook.setCarId(1966446868651413888L);
		berthBook.setBookCode("bbbbcccccc");
		berthBook.setParkCode("parkcodevalue");
		// berthBook.setPlateNo(plateNo);
		berthBook.setBookTime(new Date());
		berthBook.setOverTime(new Date());

		JSONObject params = ParamsUtils.swapParams(berthBook,
				new String[] { "bookCode", "parkCode", "plateNo", "bookTime", "overTime" }, new ParamsCallback() {
					@Override
					public String call(String paramName) {
						if ("plateNo".equals(paramName)) {
							String plateNo = PlateUtils.decode(berthBook.getCarId());
							int index = plateNo.indexOf(":");
							plateNo = index == -1 ? plateNo : plateNo.substring(index + 1);
							return plateNo;
						}
						return null;
					}
				});

		System.out.println(params.toJSONString());
	}
}
