package com.game.config.tpl;

import com.game.config.IBeanConfigurable;
import com.game.util.ToolUtils;

import java.util.Map;

/**
 * 道具配置表
 * Created by xiewen on 2016/11/25.
 */
public class ItemTpl implements IBeanConfigurable {
    private int id;     // 唯一ID
    private String name; //	道具名称
    private int type; //类型
    private String desc;//描述
    private int limitMax;//可拥有的最大数
    private int ispickup;//战斗结束后获得道具

    @Override
    public void parse(Map<String, String> templateData) {
        this.id = ToolUtils.cover2int(templateData.get("id"));
        this.name =  templateData.get("name");
        this.type = ToolUtils.cover2int( templateData.get("subType"));
        this.desc = templateData.get("desc");
//        this.limitMax = ToolUtils.cover2int( templateData.get("limitMax"));
//        this.compound_id = ToolUtils.cover2int(templateData.get("compound_id"));
//        this.ispickup = ToolUtils.cover2int(templateData.get("ispickup"));
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getLimitMax() {
		return limitMax;
	}

	public void setLimitMax(int limitMax) {
		this.limitMax = limitMax;
	}

//	public int getCompound_id() {
//		return compound_id;
//	}
//
//	public void setCompound_id(int compound_id) {
//		this.compound_id = compound_id;
//	}

	public int getIspickup() {
		return ispickup;
	}

	public void setIspickup(int ispickup) {
		this.ispickup = ispickup;
	}
}
