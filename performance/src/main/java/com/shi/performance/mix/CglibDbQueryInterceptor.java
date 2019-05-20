package com.shi.performance.mix;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CglibDbQueryInterceptor implements MethodInterceptor {

	IDbquery real = null;

	@Override
	public Object intercept(Object obj, Method method, Object[] obj2, MethodProxy proxy) throws Throwable {

		if (real == null) {
			real = new DBQuery();
		}
		return real.request();
	}

	public static IDbquery createCglibProxy() {
		Enhancer enhancer = new Enhancer();
		enhancer.setCallback(new CglibDbQueryInterceptor());// 指定切入器，定义代理类逻辑
		enhancer.setInterfaces(new Class[] { IDbquery.class });// 指定实现的接口
		IDbquery cglibProxy = (IDbquery) enhancer.create();
		return cglibProxy;
	}

}
