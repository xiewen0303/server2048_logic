package com.game.mapping.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.game.mapping.constant.EasyGroup;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EasyWorker {
	/**
	 * workerName 接收指令的action名称 默认为空,直接取类名,但防止重名
	 * @return 
	 * @date 2016-4-14 上午11:50:57
	 */
	String workerName() default "";
	
	/**
	 * moduleName 模块名称
	 * @return
	 */
	String moduleName() default "";
	
	/**
	 * groupName 指令执行组类型	{@link EasyGroup} 默认为bus模块
	 * @return
	 */
	EasyGroup groupName() default EasyGroup.BUS_CACHE;
}
