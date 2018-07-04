package com.game.service.map;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.game.context.IStore;
import com.game.service.map.model.Copy;



@Component
public class CopyStore implements IStore {
	/**
	 * uid  = 地图信息 
	 */
	private Map<Long,Copy> copyDatas = new HashMap<>();
	
	public void addCopyInfo(long uid,Copy mapInfo){
		this.copyDatas.put(uid,mapInfo);
	}
	
	public Copy getCopyInfo(long uid){
		return this.copyDatas.get(uid);
	}
}
