package fr.andromede.fluent;


import java.beans.Statement;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ObjectFluentBuilder implements InvocationHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ObjectFluentBuilder.class);
	
	private Object object;

	public ObjectFluentBuilder(Object object) {
		this.object = object;
	}

	public static <T> T create(Object object, Class<T> clazz) {
		ObjectFluentBuilder handler = new ObjectFluentBuilder(object);
		@SuppressWarnings("unchecked")
		T fluentInterface = (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, handler);
		return fluentInterface;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String methodName = method.getName();
		if ("create".equals(methodName)) {
			return this.object;
		}
		String setter = "set" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
		Statement statement = new Statement(this.object, setter, args);
		statement.execute();

		return proxy;
	}
}