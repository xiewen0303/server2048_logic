package com.game.config.tpl;

import com.game.config.TemplatesManager;

/**
 * 怪物配置表
 * Created by xiewen on 2016/11/25.
 */
public class MonsterTplService {
	
	public static MonsterTpl getMonsterTpl(int id){
		MonsterTpl itemTpl = TemplatesManager.getConfigBean(MonsterTpl.class, id);
		return itemTpl;
	}
}
