package com.game.service.bag;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.game.context.IStore;
import com.game.service.bag.model.BagInfo;
import com.game.service.bag.util.BagUtil;

import db.ibatis.bean.HeroOtherData;
import db.ibatis.bean.ItemInfo;


@Component
public class BagStore implements IStore {

    private Map<Long,BagInfo> bagMgr = new HashMap<>();
    
    private Map<Long,HeroOtherData> otherDatas = new HashMap<>();

    public BagInfo getBag(long uid) {
        return bagMgr.get(uid);
    }

    public void addBag(BagInfo bag){
        bagMgr.put(bag.getUid(),bag);
    }
    
    public HeroOtherData getOtherDatas(long uid){
    	return this.otherDatas.get(uid);
    }
    
    public void addOtherDatas(HeroOtherData info){
    	otherDatas.put(info.getUid(),info);
    }
    
    public void addBagItem(long uid,ItemInfo itemInfo){
    	getBag(uid).addItem(BagUtil.CoverItem(itemInfo));
    }
 
    
}