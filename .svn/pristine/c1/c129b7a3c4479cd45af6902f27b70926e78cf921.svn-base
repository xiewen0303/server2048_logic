package com.game.kernel.clearRule;

import com.game.service.hero.util.HeroUtil;

/**
 * 基础业务的清理规则
 * @author XieWen
 * @date 2016-4-15 上午10:21:39
 */
public class BusClearRule implements IClearRule {

	@Override
	public boolean clear(Object key) {
		long uid = (long)key;
		return !HeroUtil.isOnline(uid);
	}
}