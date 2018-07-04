package com.game.config.tpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import log.LogUtil;

import com.game.config.IBeanConfigurable;
import com.game.config.IConfigInit;
import com.game.config.TemplatesManager;

/**
 * 合成配置表
 * Created by xiewen on 2016/11/25.
 */
public class CompoundTplService implements IConfigInit {
	public static List<Integer> canMerges = new ArrayList<>();
	
	public static CompoundTpl getCompoundTplService(int id){
		return TemplatesManager.getConfigBean(CompoundTpl.class, id);
	}

	@Override
	public void init() {
		Map<Object, ? extends IBeanConfigurable> compoundTpls = TemplatesManager.getConfigBeans(CompoundTpl.class);
		if(compoundTpls == null){
			LogUtil.error("CreationTpl is null");
			return;
		}
		for (Entry<Object,? extends IBeanConfigurable> entry : compoundTpls.entrySet()) {
			CompoundTpl tpl = (CompoundTpl)entry.getValue();
			if(tpl.isCreate()){
				canMerges.add(tpl.getId());
			}
		}
	}
	
	public static List<Integer> getCanMerge() {
		return canMerges;
	}
}
