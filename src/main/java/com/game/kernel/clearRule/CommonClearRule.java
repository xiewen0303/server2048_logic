package com.game.kernel.clearRule;

/**
 * 基础业务的清理规则
 * @author XieWen
 * @date 2016-4-15 上午10:21:39
 */
public class CommonClearRule implements IClearRule {

	@Override
	public boolean clear(Object key) {
		//公共模块
		return false;
	}
}