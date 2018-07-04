package db.ibatis.bean;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.game.util.ToolUtils;

import db.ibatis.GameObject;

public class HeroOtherData extends HeroOtherDataBase {
	 
	
	/**
	 * 已经研发出来的道具
	 */
	private List<Integer> createItemList;

	public List<Integer> getCreateItemList() {
		if(createItemList == null){
			if(ToolUtils.isEmpty(super.getCreateItems())){
				createItemList = new ArrayList<>();
				this.setCreateItemList(createItemList);
			}else{
				createItemList = JSONArray.parseArray(super.getCreateItems(),Integer.class);				
			}
		}
		return createItemList;
	}

	public void setCreateItemList(List<Integer> createItemList) {
		this.createItemList = createItemList;
		if(createItemList == null){
			super.setCreateItems(null);
		}else{
			super.setCreateItems(JSONArray.toJSONString(createItemList));
		}
	}
}
