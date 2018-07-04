package com.game.config.tpl;

import com.game.config.TemplatesManager;

/**
 * 研发配置表
 * Created by xiewen on 2016/11/25.
 */
public class CreationTplService  {
	
	public static CreationTpl getCreationTplService(int id){
		return TemplatesManager.getConfigBean(CreationTpl.class, id);
	}
	
//	public static List<Integer> canCreates = new ArrayList<>();
//
//	@Override
//	public void init() {
//		Map<Object, ? extends IBeanConfigurable>  creationTpls = TemplatesManager.getConfigBeans(CreationTpl.class);
//		if(creationTpls == null){
//			LogUtil.error("CreationTpl is null");
//			return;
//		}
//		for (Entry<Object,? extends IBeanConfigurable> entry : creationTpls.entrySet()) {
//			CreationTpl tpl = (CreationTpl)entry.getValue();
//			if(tpl.getNeed_level() == 0){
//				canCreates.add(tpl.getId());
//			}
//		}
//	}
//
//	public static List<Integer> getCanCreates() {
//		return canCreates;
//	}
}
