package com.game.config.tpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.game.config.IBeanConfigurable;
import com.game.config.TemplatesManager;

/**
 * 地图配置表
 * Created by xiewen on 2016/11/25.
 */
public class MapChildTplService {
	
	public static MapChildTpl getMapChildTpl(int childId){
		MapChildTpl itemTpl = TemplatesManager.getConfigBean(MapChildTpl.class, childId);
		return itemTpl;
	}

	public static List<MapChildTpl> getMapChildTpls(int mapId) {
		List<MapChildTpl> result = new ArrayList<>();
		Map<Object, ? extends IBeanConfigurable>  datas =  TemplatesManager.getConfigBeans(MapChildTpl.class);
		if(datas == null){
			return null;
		}
		for (IBeanConfigurable element : datas.values()) {
			MapChildTpl mapChildTpl = (MapChildTpl)element;
			if(mapChildTpl.getMapId() == mapId){
				result.add(mapChildTpl);
			}
		}
		return result;
	}
}
