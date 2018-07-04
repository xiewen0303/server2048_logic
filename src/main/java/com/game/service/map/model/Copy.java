package com.game.service.map.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 地图
 * @author wind
 */
public class Copy {
	
	/**
	 * 当前进度ID 子Id
	 */
	private transient int maxCopyTplId; 
	/**
	 * 父地图
	 */
	private Map<Integer,ParentCopy> parentCopys = new HashMap<>(); 
	/**
	 * 最大可以带入道具进入背包的道具数
	 */
	private int maxItemSolt;
	
	public int getMaxCopyTplId() {
		return maxCopyTplId;
	}
	public void setMaxCopyTplId(int setMaxCopyTplId) {
		this.maxCopyTplId = setMaxCopyTplId;
	}
	public Map<Integer, ParentCopy> getParentCopys() {
		return parentCopys;
	}
	public void setParentCopys(Map<Integer, ParentCopy> parentCopys) {
		this.parentCopys = parentCopys;
	}
	
	public void addParentCopy(ParentCopy parentCopy){
		this.parentCopys.put(parentCopy.getMapTplId(), parentCopy);
	}
	public int getMaxItemSolt() {
		return maxItemSolt;
	}
	public void setMaxItemSolt(int maxItemSolt) {
		this.maxItemSolt = maxItemSolt;
	}
}