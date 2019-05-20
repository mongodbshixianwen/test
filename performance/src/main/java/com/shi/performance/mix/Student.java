package com.shi.performance.mix;

import java.util.Vector;

public class Student implements java.lang.Cloneable {

	private int id;
	private String name;
	private Vector courses;
	 //getter  setter 省略...
	public Student newInstance() {
		try {
			return (Student) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return null;
	}
}
