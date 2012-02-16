package com.github.kivelae.genome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeatureContext {

	static Map<Class, List<Object>> data;
	
	public static void initialize() {
		data = new HashMap<Class, List<Object>>();
	}
	
	public static <T> void add(T value) {
		if (!data.containsKey(value.getClass())) {
			data.put(value.getClass(), new ArrayList<Object>());
		}
		data.get(value.getClass()).add(value);
	}
	
	public static <T> List<T> get(Class clazz) {
		return (List<T>) data.get(clazz);
	}

	public static <T> T getFirst(Class<T> clazz) {
		return (T) get(clazz).get(0);
	}
}
