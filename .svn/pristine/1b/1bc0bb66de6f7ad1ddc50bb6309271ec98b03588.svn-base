package com.game.config.tpl;

import java.util.Map;

import com.game.config.IBeanConfigurable;
import com.game.constant.GlobalConst;
import com.game.util.ToolUtils;

/**
 * 研发配置表
 * Created by xiewen on 2016/11/25.
 */
public class CreationTpl implements IBeanConfigurable {
	
    private int id;     // 唯一ID
    private Map<Integer,Integer> need_data; //	需要消耗的道具
    private int need_level;//需要的父类关卡等级
    
    @Override
    public void parse(Map<String, String> templateData) {
        this.id = ToolUtils.cover2int(templateData.get("id"));
        this.need_data = ToolUtils.parseMap(templateData.get("needItem"), GlobalConst.FEN_HAO,  GlobalConst.DOU_HAO);
        this.need_level =  ToolUtils.cover2int(templateData.get("level"));;
    }

    @Override
    public Object getTemplateId() {
        return this.id;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<Integer, Integer> getNeed_data() {
		return need_data;
	}

	public void setNeed_data(Map<Integer, Integer> need_data) {
		this.need_data = need_data;
	}

	public int getNeed_level() {
		return need_level;
	}

	public void setNeed_level(int need_level) {
		this.need_level = need_level;
	}
}
