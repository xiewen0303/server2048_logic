package com.game.config.tpl;

import com.game.config.TemplatesManager;

/**
 * 地图配置表
 * Created by xiewen on 2016/11/25.
 */
public class MapBaseTplService {
	
	public static MapBaseTpl getItemTpl(int id){
		MapBaseTpl itemTpl = TemplatesManager.getConfigBean(MapBaseTpl.class, id);
		return itemTpl;
	}
}
