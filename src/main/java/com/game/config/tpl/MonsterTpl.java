package com.game.config.tpl;

import java.util.Map;

import com.game.config.IBeanConfigurable;
import com.game.constant.GlobalConst;
import com.game.util.ToolUtils;

/**
 * 怪物配置表
 * Created by xiewen on 2016/11/25.
 */
public class MonsterTpl implements IBeanConfigurable {

    private int id;     // 唯一ID
    private String name; //	道具名称
    private String desc;//描述
    private int use_grid_count; //类型
    private int hp;
    private int[] skills;
    private int[] cd;
    
    @Override
    public void parse(Map<String, String> templateData) {
        this.id = ToolUtils.cover2int(templateData.get("id"));
        this.name =  templateData.get("name");
        this.hp = ToolUtils.cover2int(templateData.get("hp"));
        this.desc = templateData.get("desc");
        this.use_grid_count = ToolUtils.cover2int(templateData.get("use_grid_count"));
        this.skills = ToolUtils.parseIntArray(templateData.get("skills"), GlobalConst.DOU_HAO);
        this.cd = ToolUtils.parseIntArray(templateData.get("cd"), GlobalConst.DOU_HAO);
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getUse_grid_count() {
		return use_grid_count;
	}

	public void setUse_grid_count(int use_grid_count) {
		this.use_grid_count = use_grid_count;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int[] getSkills() {
		return skills;
	}

	public void setSkills(int[] skills) {
		this.skills = skills;
	}

	public int[] getCd() {
		return cd;
	}

	public void setCd(int[] cd) {
		this.cd = cd;
	}
}
