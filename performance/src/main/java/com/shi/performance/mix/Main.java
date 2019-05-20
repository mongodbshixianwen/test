package com.shi.performance.mix;

public class Main {

	public static void main(String[] args) throws Exception {

		// jdk
		System.out.println("jdk动态代理：" + JdkDbQueryHandler.createJdkProxy().request());
		// cglib
		System.out.println("cglib动态代理" + CglibDbQueryInterceptor.createCglibProxy().request());

		// javassist 代理工厂创建
		System.out.println("javassist 代理工厂创建" + JavassistDynDbQueryHandler.createJavassistDynProxy().request());

		// javassist 动态创建代理
		System.out.println("javassist 动态创建代理" + JavassistDynDbQueryHandler.createJavassistBytecodeDynamicProxy().request());
	}
}
