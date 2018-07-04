package com.game.core.lock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 锁资源(可以采用读写锁,可优化地)
 * @author XieWen
 * @date 2015-11-5 下午3:33:31
 */
public class LockInfo {
	private Map<String,Object> locks = new ConcurrentHashMap<>();
	/**
	 * 获得对应可以的锁
	 * @param key
	 * @return
	 */
	public Object getLock(String key){
		Object obj = locks.get(key);
		if(obj == null){
			obj = new Object();
			locks.put(key, obj);
		}
		return obj;
	}
}
