package com.game.core.lock;

public class LockUtil {
	
	/**
	 * 获取锁对象
	 * @param uid 用户Id
	 * @param lockType {@link LockType}
	 * @return
	 */
	public static Object getLock(long uid,String lockType){
		return LockManager.getLockManager().getLockObj(uid, lockType);
	}
}
