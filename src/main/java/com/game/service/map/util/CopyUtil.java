package com.game.service.map.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.game.service.map.model.ChildCopy;
import com.game.service.map.model.ParentCopy;

import db.ibatis.bean.CopyInfo;

/**
 * 地图工具类
 * @author xiewen
 */
public class CopyUtil {
	
	static List<Integer> newbieCopyIds =Arrays.asList(
			1001,
			1101,
			1201,
			10001,
			10002,
			10003);
	
	public static boolean isNewbieCopy(int tplId){
		return newbieCopyIds.contains(tplId);
	}

	public static CopyInfo cover2CopyInfo(long uid,ParentCopy parentCopy) {
		CopyInfo copyInfo = new CopyInfo();
		copyInfo.setUid(uid);
		copyInfo.setMapTplId(parentCopy.getMapTplId());
		copyInfo.setState(parentCopy.getState());
		copyInfo.setChildCopys(JSONObject.toJSONString(parentCopy.getChildCopys()));
		return copyInfo;
	}

	public static ParentCopy cover2ParentCopy(CopyInfo c) {
		ParentCopy parentCopy = new ParentCopy();
		if(c.getChildCopys() != null){
			parentCopy.setChildCopys(JSONObject.parseObject(c.getChildCopys(), new TypeReference<Map<Integer,ChildCopy>>(){}));	
		}
		parentCopy.setMapTplId(c.getMapTplId());
		parentCopy.setState(c.getState());
		return parentCopy;
	}
}
