package com.shi.performance.mix;

import java.util.Vector;

public class StuMain {

	public static void main(String[] args) {
		Student stu1 = new Student();
		Vector cs = new Vector();
		cs.add("java");
		
		stu1.setId(1);
		stu1.setName("XiaoMing");
		stu1.setCourses(cs);
		
		
		Student stu2 = stu1.newInstance();
		stu2.setId(2);
		stu2.setName("XiaoDong");
		stu2.getCourses().add("c#");
		
		System.out.println("stu1'name:"+stu1.getName());
		System.out.println("stu2'name:"+stu2.getName());
		System.out.println(stu1.getCourses() == stu2.getCourses());
	}
}
