package com.game.context;

import com.game.mapping.manage.MappingContainer;

/**
 * 获取其他模块的缓存数据
 * @author XieWen
 * @date 2016-4-15 下午5:21:10
 */
public class Stores {
	
	public static <T extends IStore> T getStore(Class<T> clazz){
		return MappingContainer.getWorker(clazz);
	}
	
}