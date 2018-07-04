package com.game.context;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 加载spring
 * @author XieWen
 * @date 2016-3-17 上午10:04:00
 */
public class SpringHandler {
	
	private static SpringHandler springLoadHandler = new SpringHandler();
	
	private ApplicationContext context;
	
	private SpringHandler(){
		init();
	}
	

	public static SpringHandler loadSpring(){
		return springLoadHandler;
	}

	private void init() {
		context = new ClassPathXmlApplicationContext("config/applicationContext.xml");
//		Daos.setDao((Dao)context.getBean("dao"));
	}

	public ApplicationContext getSpringContext(){
		return context;
	}
}