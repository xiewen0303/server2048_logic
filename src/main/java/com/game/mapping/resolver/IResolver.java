package com.game.mapping.resolver;

/**
 * 跳转到制定的action
 * @author XieWen
 * @date 2016-4-14 上午11:02:17
 */
public interface IResolver {
	
	/**
	 * 执行业务
	 * @param 消息
	 * @date 2016-4-14 上午11:02:00
	 */
	public void execute(Object message);
}
