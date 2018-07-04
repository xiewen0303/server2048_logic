package com.game.service.bag.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BagInfo {

	private long uid;
	private Map<Long,IGoods> goodmgr = new HashMap<>();

	public void addItem(IGoods goods){
		goodmgr.put(goods.getUuid(),goods);
	}

	@SuppressWarnings("unchecked")
	public <T extends  IGoods> T getItem(long uuid) {
		return (T) goodmgr.get(uuid);
	}

	public List<IGoods> getItems() {
		return new ArrayList<>(goodmgr.values());
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}
}