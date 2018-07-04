package com.game.gen;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.game.config.ProperitesConfig;
import com.game.config.ProperitesConst;
import com.game.context.Daos;
import db.ibatis.bean.UuidInfo;

/**
 * 条件是必须启动时执行init方法
 */
public class PrimaryKeyGen {
	/**
	 * 预计最大已100万个服务器
	 * 8796093022209  主键最多
	 */
	private static final int MOVE_BIT = 42;
	
	private static PrimaryKeyGen primaryKeyGen = new PrimaryKeyGen();
	
	private Map<Integer,Long> ids = new ConcurrentHashMap<>();
	
	private PrimaryKeyGen() {
	}
	
	public static PrimaryKeyGen getPrimaryKeyGen(){
		return primaryKeyGen;
	}
	
	/**
	 * 初始化
	 * @param
	 */
	public void init(){
		List<UuidInfo> genInfos = Daos.getDao().getListByCondition(UuidInfo.class, "");
		for (UuidInfo genInfo : genInfos) {
			ids.put(genInfo.getType(), genInfo.getId());
		}
	}

	/**
	 * 生成的唯一key
	 * @param primaryType {@link PrimaryType }
	 * @return
	 */
	public long getPk(PrimaryType primaryType,boolean isStoreDB) {
		boolean flag = false;
		Long lastId = 0L;
		synchronized(primaryType){
			lastId = ids.get(primaryType.getType());
			if(lastId == null){
				flag = true;
				int serverId = ProperitesConfig.getInt(ProperitesConst.SERVER_BASE, "SERVER_ID");
				lastId = (long)serverId << MOVE_BIT;
				
				//如果需要从负数算
				if(primaryType.isMinus()){
					lastId = lastId*-1;
				}
			}
			if(primaryType.isMinus()){
				ids.put(primaryType.getType(), --lastId);
			} else{
				ids.put(primaryType.getType(), ++lastId);
			}
		}

		if (isStoreDB) {
			UuidInfo uuidInfo = new UuidInfo();
			uuidInfo.setId(lastId);
			uuidInfo.setType(primaryType.getType());
			if(flag){
				uuidInfo.insert();
			}else{
				uuidInfo.update();
			}
		}
		return lastId;
	}
}