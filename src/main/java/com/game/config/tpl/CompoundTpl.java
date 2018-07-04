package com.game.config.tpl;

import java.util.Map;

import com.game.config.IBeanConfigurable;
import com.game.constant.GlobalConst;
import com.game.util.ToolUtils;

/**
 * 合成配置表
 * Created by xiewen on 2016/11/25.
 */
public class CompoundTpl implements IBeanConfigurable {
	
    private int id;     // 唯一ID
    private Map<Integer,Integer> need_data; //	需要消耗的道具
    private boolean isCreate;
    
    @Override
    public void parse(Map<String, String> templateData) {
        this.id = ToolUtils.cover2int(templateData.get("id"));
        this.need_data = ToolUtils.parseMap(templateData.get("needItem"), GlobalConst.FEN_HAO,  GlobalConst.DOU_HAO);
        this.isCreate = ToolUtils.cover2int(templateData.get("open")) == 1;
    }

    public boolean isCreate() {
		return isCreate;
	}

	public void setCreate(boolean isCreate) {
		this.isCreate = isCreate;
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
}
