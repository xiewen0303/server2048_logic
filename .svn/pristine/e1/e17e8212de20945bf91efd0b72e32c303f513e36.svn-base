package com.game.service.map.model;

import java.util.HashMap;
import java.util.Map;

public class ParentCopy {
	private int mapTplId;//地图配置Id
	private int state;  //三星奖励，1:可领奖  2:未完成  3:已领奖
	private Map<Integer,ChildCopy> childCopys = new HashMap<>(); //进度ID
	
	public int getMapTplId() {
		return mapTplId;
	}
	public void setMapTplId(int mapTplId) {
		this.mapTplId = mapTplId;
	}
	public Map<Integer, ChildCopy> getChildCopys() {
		return childCopys;
	}
	public void setChildCopys(Map<Integer, ChildCopy> childCopys) {
		this.childCopys = childCopys;
	}
	
	/**
	 * 三星奖励，1:可领奖  2:未完成  3:已领奖
	 * @return
	 */
	public int getState() {
		return state;
	}
	/**
	 * 三星奖励，1:可领奖  2:未完成  3:已领奖
	 * @param state
	 */
	public void setState(int state) {
		this.state = state;
	}
	public void addChildCopy(ChildCopy childCopy) {
		this.childCopys.put(childCopy.getMapTplId(), childCopy);
	}
}
