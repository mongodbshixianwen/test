package com.shi.performance.mix;

import java.lang.reflect.Method;

import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtNewConstructor;
import org.apache.ibatis.javassist.CtNewMethod;
import org.apache.ibatis.javassist.util.proxy.MethodHandler;
import org.apache.ibatis.javassist.util.proxy.ProxyFactory;
import org.apache.ibatis.javassist.util.proxy.ProxyObject;

public class JavassistDynDbQueryHandler implements MethodHandler {

	IDbquery real = null;

	@Override
	public Object invoke(Object arg0, Method arg1, Method arg2, Object[] arg3) throws Throwable {
		if (real == null) {
			real = new DBQuery();
		}
		return real.request();
	}

	/**
	 * 代理工厂创建
	 * 
	 * @return
	 * @throws Exception
	 */
	public static IDbquery createJavassistDynProxy() throws Exception {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setInterfaces(new Class[] { IDbquery.class });
		Class proxyClass = proxyFactory.createClass();
		IDbquery javassistProxy = (IDbquery) proxyClass.newInstance();

		((ProxyObject) javassistProxy).setHandler(new JavassistDynDbQueryHandler());
		return javassistProxy;
	}

	/**
	 * 使用动态JAVA代码创建代理的过程，生成字节码，可以在运行时生成业务逻辑
	 * 
	 * @return
	 * @throws Exception
	 */
	public static IDbquery createJavassistBytecodeDynamicProxy() throws Exception {

		ClassPool mPool = new ClassPool(true);
		// 定义类名
		CtClass mCtc = mPool.makeClass(IDbquery.class.getName() + "Javassist-BytecodeProxy");
		// 需要实现的接口
		mCtc.addInterface(mPool.get(IDbquery.class.getName()));
		// 添加构造函数
		mCtc.addConstructor(CtNewConstructor.defaultConstructor(mCtc));
		// 添加类的字段信息，使用动态JAVA代码
		mCtc.addField(org.apache.ibatis.javassist.CtField.make("public " + IDbquery.class.getName() + " real;", mCtc));

		String dbQueryName = DBQuery.class.getName();
		// 添加方法，这里使用动态JAVA代码指定内部逻辑
		mCtc.addMethod(CtNewMethod.make(
				"public String request(){ if(real == null ) real = new " + dbQueryName + "();return real.request();}",
				mCtc));

		// 基于以上动态信息生成动态类
		Class pc = mCtc.toClass();

		// 生成动态类的实例
		IDbquery bytecodeProxy = (IDbquery) pc.newInstance();

		return bytecodeProxy;

	}
}
