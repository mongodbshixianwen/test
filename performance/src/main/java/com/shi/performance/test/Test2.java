package com.shi.performance.test;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zhht.das.BerthBook;
import com.zhht.utils.SHA1;

public class Test2 {

	public static void main(String[] args) throws Exception {

		BerthBook berthBook = new BerthBook();
		berthBook.setCarId(1966446868651413888L);
		berthBook.setBookCode("bbbbcccccc");
		berthBook.setParkCode("parkcodevalue");
		// berthBook.setPlateNo(plateNo);
		berthBook.setBookTime(new Date());
		berthBook.setOverTime(new Date());

		JSONObject obj = new JSONObject();
		obj.put("lease", berthBook);
		obj.put("status", 0);

		JSONArray data = new JSONArray();
		data.add(obj);

		String dataStr = JSON.toJSONString(data, SerializerFeature.WriteMapNullValue);
		System.out.println("签名前====="+dataStr);
		String signature = SHA1.getInstance().signature(dataStr);
		System.out.println("得到签名====="+signature);
		JSONObject params = new JSONObject();
		params.put("timestamp", System.currentTimeMillis());
		params.put("sign", signature);
		params.put("data", data);

		System.out.println(params.toJSONString());
	}
}
