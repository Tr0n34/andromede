package fr.andromede.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
	
	private static final Logger logger = LoggerFactory.getLogger(Utils.class);
	public static final String GET_PREFIX = "get";
	public static final String SET_PREFIX = "set";
	public static final String GETCLASS_METHOD = "getClass";


	public static Map<String, Object> mapAttributes(Object object) {
		Map<String, Object> mapAttributes = new HashMap<String, Object>();
		Method[] methods = object.getClass().getMethods();
		String name = "";
		for (int i = 0; i < methods.length; i++) {
			name = methods[i].getName();
			if ((((name != null ? 1 : 0) & (!"".equals(name) ? 1 : 0)) != 0) && (name.length() > 3) && 
					("get".equals(name.substring(0, 3))) && (!"getClass".equals(name))) {
				try {
					mapAttributes.put(name.substring(3), methods[i].invoke(object, (Object[])null));
				} catch (IllegalAccessException e) {
					logger.error(e.getMessage(), e);
				} catch (IllegalArgumentException e) {
					logger.error(e.getMessage(), e);
				} catch (InvocationTargetException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}

		return mapAttributes;
	}

	public static <L extends List<E>, E> String toString(L list) {
		StringBuilder builder = new StringBuilder();
		for (E element : list ) {
			builder.append(toString(element));
		}
		return builder.toString();
	}

	public static String toString(Object object) {
		if ( object == null ) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		builder.append(new StringBuilder().append(object.getClass().getSimpleName()).append(" [").toString());
		Method[] methods = object.getClass().getMethods();
		Map<String, Object> map = new HashMap<String, Object>();
		String name = "";
		for ( int i = 0; i < methods.length; i++ ) {
			name = methods[i].getName();
			if ((name != null) && (!"".equals(name)) && (name.length() > 3) && 
					("get".equals(name.substring(0, 3))) && (!"getClass".equals(name))) {
				name = name.substring(3, name.length());
				map.put(name, methods[i]);
			}
		}

		int lastGetter = 0;

		for ( Entry<String, Object> entry : map.entrySet()) {
			try {
				lastGetter++;
				builder.append(new StringBuilder().append((String)entry.getKey()).append("=").append(((Method)entry.getValue()).invoke(object, (Object[])null)).toString());
				if (lastGetter == map.size())
					builder.append("]");
				else
					builder.append(",");
			}
			catch (IllegalAccessException e) {
				logger.error(e.getMessage(), e);
			} catch (IllegalArgumentException e) {
				logger.error(e.getMessage(), e);
			} catch (InvocationTargetException e) {
				logger.error(e.getMessage(), e);
			}
		}

		return builder.toString();
	}
}