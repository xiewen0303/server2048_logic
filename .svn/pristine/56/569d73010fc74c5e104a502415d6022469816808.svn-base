package com.game.mapping.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.game.mapping.constant.EasyKuafuType;

/**
 * 指令映射注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EasyMapping {
	/**
	 * mapping 指令(必填值)
	 * @return
	 */
	short mapping();

	/**
	 * kuafuType 跨服指令类型	{@link EasyKuafuType}
	 * @return
	 */
	EasyKuafuType kuafuType() default EasyKuafuType.NOT_KUAFU_TYPE;
}
