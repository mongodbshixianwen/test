package com.shi.performance.test;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zhht.das.BerthBook;
import com.zhht.utils.SHA1;

public class Test3 {

	public static void main(String[] args) throws Exception {

		BerthBook berthBook = new BerthBook();
		berthBook.setBerthBookId(null);
		berthBook.setBerthCode(null);
		berthBook.setBerthId(null);
		berthBook.setGarageCode(null);
		berthBook.setGarageId(null);
		berthBook.setMessage(null);
		berthBook.setOverTime(new Date(1556274480414L));
		berthBook.setParkCode("parkcodevalue");
		berthBook.setParkId(null);
		berthBook.setPlateNo(null);
		berthBook.setRestReservedStalls(null);
		berthBook.setStatus(null);
		berthBook.setBluetoothAddress(null);
		berthBook.setBookCode("bbbbcccccc");
		berthBook.setBookTime(new Date(1556274480414L));
		berthBook.setCarId(1966446868651413888L);
		berthBook.setConfirmStatus(null);
		berthBook.setConfirmTime(null);
		berthBook.setCreateTime(null);
		
		
		// berthBook.setPlateNo(plateNo);

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
//		params.put("timestamp", System.currentTimeMillis());
//		params.put("sign", signature);
		params.put("data", data);
		String dataStr2 = JSON.toJSONString(params, SerializerFeature.WriteMapNullValue);
		String signature2 = SHA1.getInstance().signature(dataStr2);

		System.out.println("得到签名2====="+signature2);
		System.out.println(params.toJSONString());
	}
}
