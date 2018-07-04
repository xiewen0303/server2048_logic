package com.game.config.tpl;

import java.util.Map;

import com.game.config.IBeanConfigurable;
import com.game.constant.GlobalConst;
import com.game.util.ToolUtils;

/**
 * 地图配置表
 * Created by xiewen on 2016/11/25.
 */
public class MapBaseTpl implements IBeanConfigurable {
	 private int id;
	 private String map_name;
	 private String desc;
	 private Map<Integer,Integer> star1_reward;
	 private Map<Integer,Integer> star2_reward;
	 private Map<Integer,Integer> star3_reward;
	 
	 
    @Override
    public void parse(Map<String, String> templateData) {
        this.id = ToolUtils.cover2int(templateData.get("id"));
        this.map_name =  templateData.get("name");
        this.desc = templateData.get("desc");
        this.star1_reward = ToolUtils.parseMap(templateData.get("star1_reward"),GlobalConst.FEN_HAO,GlobalConst.DOU_HAO);
        this.star2_reward = ToolUtils.parseMap(templateData.get("star2_reward"),GlobalConst.FEN_HAO,GlobalConst.DOU_HAO);
        this.star3_reward = ToolUtils.parseMap(templateData.get("star3_reward"),GlobalConst.FEN_HAO,GlobalConst.DOU_HAO);
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

	public String getMap_name() {
		return map_name;
	}

	public void setMap_name(String map_name) {
		this.map_name = map_name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Map<Integer, Integer> getStar1_reward() {
		return star1_reward;
	}

	public Map<Integer, Integer> getStar2_reward() {
		return star2_reward;
	}

	public Map<Integer, Integer> getStar3_reward() {
		return star3_reward;
	}
}
