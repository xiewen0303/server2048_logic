package com.game.service.bag.util;

import com.game.service.bag.model.Item;
import db.ibatis.bean.ItemInfo;

/**
 * bagUtil
 * Created by xiewen on 2016/11/9.
 */
public class BagUtil {

    public static Item CoverItem(ItemInfo itemInfo){
        Item item = new Item();
        item.setItemCount(itemInfo.getCount());
        item.setTplId(itemInfo.getTplId());
        item.setUuid(itemInfo.getUuid());
        item.setUid(itemInfo.getUid());
        return item;
    }

    public static ItemInfo CoverItemInfo(Item item) {
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.setCount(item.getItemCount());
        itemInfo.setTplId(item.getTplId());
        itemInfo.setUuid(item.getUuid());
        itemInfo.setUid(item.getUid());
        return itemInfo;
    }
}
