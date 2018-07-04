package com.game.config;

import java.util.HashMap;

/**
 * 简单的BEAN LOADER
 * 
 * @author wyan
 * 
 */
public class BeanLoader {

	public final static String GM = "gm";

	private final static HashMap<String, HashMap<String, ? extends Object>> beans = new HashMap<String, HashMap<String, ? extends Object>>();

	public static HashMap<String, ? extends Object> getBeans(String category) {
		return beans.get(category);
	}

	public static Object getBean(String category, String beanName) {
		return beans.get(category).get(beanName);
	}

	public static void load() {

		try {
			//loadGmBeans();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
