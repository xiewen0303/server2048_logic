package com.game.mapping.manage;

import org.springframework.context.ApplicationContext;

import com.game.context.SpringHandler;


public class MappingContainer   {
	
	public static <T> T getWorker(Class<T> workerClass) {
		ApplicationContext context = SpringHandler.loadSpring().getSpringContext();
		return context.getBean(workerClass);
	}

	public static Object getWorker(String beanName) {
		ApplicationContext context = SpringHandler.loadSpring().getSpringContext();
		return context.getBean(beanName);
	}
	
}