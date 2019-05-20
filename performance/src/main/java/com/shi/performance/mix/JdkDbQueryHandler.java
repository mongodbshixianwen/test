package com.shi.performance.mix;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkDbQueryHandler implements InvocationHandler {

	IDbquery real = null;

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (real == null) {
			real = new DBQuery();
		}
		return real.request();
	}

	public static IDbquery createJdkProxy() {
		IDbquery jdkProxy = (IDbquery) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
				new Class[] { IDbquery.class }, new JdkDbQueryHandler());
		return jdkProxy;
	}

}
