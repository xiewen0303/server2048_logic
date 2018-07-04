package com.game.core.lock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 锁管理对象
 * @author XieWen
 * @date 2015-11-5 下午3:31:49
 */
public class LockManager {

	/**
	 * 用户锁仅存在线玩家的数据
	 */
	private Map<Long,LockInfo> userLockInfos = new ConcurrentHashMap<Long, LockInfo>();
	
	private static LockManager lockManager= new LockManager();

//	/**
//	 * 模块锁，公共锁
//	 */
//	private LockInfo moduleLockInfo = new LockInfo();
	
	
	private LockManager() { 
	}
	
	public static LockManager getLockManager(){
		return lockManager;
	} 
	
	
	/**
	 * 获得当前的玩家的该key的锁
	 * @param uid
	 * @param key
	 * @return
	 */
	public Object getLockObj(long uid,String key){
		LockInfo lockInfo = userLockInfos.get(uid);
		if(lockInfo == null){
			lockInfo = new LockInfo();
			userLockInfos.put(uid, lockInfo);
		}
		return lockInfo.getLock(key);
	}
	
	/**
	 * 下线清除锁信息
	 * @param uid
	 */
	public void offline(long uid){
		userLockInfos.remove(uid);
	}
}
