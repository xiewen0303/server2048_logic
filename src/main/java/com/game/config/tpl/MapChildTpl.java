package com.game.config.tpl;

import com.game.config.IBeanConfigurable;
import com.game.constant.GlobalConst;
import com.game.util.ToolUtils;

import java.util.HashMap;
import java.util.Map;

import log.LogUtil;

/**
 * 地图子地图置表
 * Created by xiewen on 2016/11/25.
 */
public class MapChildTpl implements IBeanConfigurable {
    private int id;     // 唯一ID
    private String map_child_name; //	道具名称
    private String desc;//描述
    private int mapId; //类型
    private Map<Integer,Integer> monster_item_random;//可拥有的最大数
    private Map<Integer,Integer> init_monster_item;//可以合成的道具id
    private Map<Integer,int[]> pass_condition;//战斗结束后获得道具
    
    @Override
    public void parse(Map<String, String> templateData) {
        try {
			this.id = ToolUtils.cover2int(templateData.get("id"));
			this.map_child_name =  templateData.get("name");
			this.mapId = ToolUtils.cover2int( templateData.get("mapId"));
			this.desc = templateData.get("desc");
			this.monster_item_random = ToolUtils.parseMap(templateData.get("monster"), GlobalConst.FEN_HAO, GlobalConst.DOU_HAO);
			this.init_monster_item = ToolUtils.parseMap(templateData.get("initMonster"), GlobalConst.FEN_HAO, GlobalConst.DOU_HAO);
			
			String passConditionStr = templateData.get("condition");
			
			Map<Integer,int[]> passConditionResult = new HashMap<>();
			String[] data1 = passConditionStr.split(GlobalConst.FEN_HAO);
			for(String data2 : data1){
				String[] datas = data2.split(GlobalConst.DOU_HAO);
				
				int[] t = new int[datas.length-1];
				for(int k=1; k < datas.length; k++){
					t[k-1]=ToolUtils.cover2int(datas[k]);
				}
				passConditionResult.put(ToolUtils.cover2int(datas[0]),t);
			}
			this.pass_condition = passConditionResult;
		} catch (Exception e) {
			LogUtil.error("==========================id:"+id);
			e.printStackTrace();
		}
    }

    @Override
    public Object getTemplateId() {
        return this.id;
    }


    public void setId(int id) {
        this.id = id;
    }

	public String getMap_child_name() {
		return map_child_name;
	}

	public void setMap_child_name(String map_child_name) {
		this.map_child_name = map_child_name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getMapId() {
		return mapId;
	}

	public void setMapId(int mapId) {
		this.mapId = mapId;
	}

	public Map<Integer, Integer> getMonster_item_random() {
		return monster_item_random;
	}

	public void setMonster_item_random(Map<Integer, Integer> monster_item_random) {
		this.monster_item_random = monster_item_random;
	}

	public Map<Integer, Integer> getInit_monster_item() {
		return init_monster_item;
	}

	public void setInit_monster_item(Map<Integer, Integer> init_monster_item) {
		this.init_monster_item = init_monster_item;
	}

	public Map<Integer, int[]> getPass_condition() {
		return pass_condition;
	}

	public void setPass_condition(Map<Integer, int[]> pass_condition) {
		this.pass_condition = pass_condition;
	}

	public int getId() {
		return id;
	}
}
