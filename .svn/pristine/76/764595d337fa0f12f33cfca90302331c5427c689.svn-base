package com.game.service.bag.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import log.LogUtil;
import message.Bag;
import message.Bag.SC_CreateItem;
import message.Bag.SC_MergeItem;
import message.common.Common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.config.tpl.CompoundTpl;
import com.game.config.tpl.CompoundTplService;
import com.game.config.tpl.CreationTpl;
import com.game.config.tpl.CreationTplService;
import com.game.constant.AppCode;
import com.game.context.Daos;
import com.game.context.ServiceManger;
import com.game.core.IPlayer;
import com.game.core.IService;
import com.game.gen.GenUtil;
import com.game.gen.PrimaryType;
import com.game.service.bag.BagStore;
import com.game.service.bag.model.BagInfo;
import com.game.service.bag.model.IGoods;
import com.game.service.bag.model.Item;
import com.game.service.bag.model.LogSource;
import com.game.service.bag.util.BagUtil;
import com.game.service.map.service.CopyService;
import com.game.util.MessageUtil;
import com.game.util.ToolUtils;

import db.ibatis.bean.HeroOtherData;
import db.ibatis.bean.ItemInfo;

@Service
public class BagService implements IService {

	@Autowired
	private BagStore bagStore;

	public List<IGoods> getBagItems(long uid){
		BagInfo bag = bagStore.getBag(uid);
		List<IGoods> goods = bag.getItems();
		return  goods;
	}
	
	public boolean checkRemoveGoods(long uid,int itemTplId,int count){
		Item item = getBagItems(uid, itemTplId);
		if(item == null){
			LogUtil.debug("checkRemoveGoods item is null,uid="+uid+"\ttplTplId="+itemTplId);
			return false;
		}
		return item.getItemCount() >= count;
	}
	
	public boolean checkRemoveGoods(long uid,Map<Integer,Integer> goods){
		if(goods == null){
			LogUtil.debug("checkRemoveGoods goods is null !");
			return true;
		}
		for (Entry<Integer, Integer> entry : goods.entrySet()) {
			int itemTplId = entry.getKey();
			int count = entry.getValue();
			if(!checkRemoveGoods(uid, itemTplId,count)){
				return false;
			}
		}
		return true;
	}
	
	public boolean removeGoods(long uid,int itemTplId,int count){
		Item item = getBagItems(uid, itemTplId);
		if(item == null){
			LogUtil.debug("removeGoods item is null,uid="+uid+"\ttplTplId="+itemTplId);
			return false;
		}
		if(item.getItemCount() < count){
			LogUtil.error("removeGoods is not enough uid="+uid+"\ttplTplId="+itemTplId);
			return false;
		}
		item.setItemCount(item.getItemCount() - count);
		
		if(item.getItemCount() <= 0){
			BagUtil.CoverItemInfo(item).delete();
		}else{
			BagUtil.CoverItemInfo(item).update();
		}
		//通知更新
		notifyUpdate(uid, item);
		return true;
	}

	private void notifyUpdate(long uid, Item item) {
		Bag.SC_UpdateItemInfos.Builder updateMsg = Bag.SC_UpdateItemInfos.newBuilder();
		message.common.Common.ItemInfo.Builder itemInfoMsg = message.common.Common.ItemInfo.newBuilder();
		itemInfoMsg.setItemCount(item.getItemCount());
		itemInfoMsg.setItemId(item.getItemTplId());
		updateMsg.addItemInfos(itemInfoMsg);
		MessageUtil.send2Client(uid, updateMsg.build(),AppCode.SUCCESS);
	}
	
	public boolean removeGoods(long uid,Map<Integer,Integer> goods){
		if(ToolUtils.isEmpty(goods)){
			return true;
		}
		
		if(!checkRemoveGoods(uid, goods)){
			return false;
		}
		for (Entry<Integer, Integer> entry : goods.entrySet()) {
			removeGoods(uid, entry.getKey(), entry.getValue());
		}
		return true;
	}
	
	
	public <T extends  IGoods> T getBagItems(long uid,long itemUUid){
		BagInfo bag = bagStore.getBag(uid);
		return bag.getItem(itemUUid);
	}
	
	public <T extends  IGoods> T getBagItems(long uid,int itemTplId){
		BagInfo bag = bagStore.getBag(uid);
		for (IGoods element : bag.getItems()) {
			if(element.getItemTplId() == itemTplId){
				return (T) element;
			} 
		}
		return null;
	}

	public void addBagItems(long uid,Map<Integer,Integer> items, LogSource source) {
		if(ToolUtils.isEmpty(items)) {
			LogUtil.error("addBagItems items is empty");
			return ;
		}
		for(Map.Entry<Integer,Integer> entry : items.entrySet()) {
			addBagItem(uid,entry.getKey(),entry.getValue(),source);
		}
	}

	public void addBagItem(long uid,int tplId,int count,LogSource source) {
		if(count <= 0 ){
			LogUtil.error("add bagItem count  < 0 ,count:" + count);
			return;
		}
		Item item = getBagItems(uid, tplId);
		if (item == null) {
			item = new Item();
			item.setTplId(tplId);
			item.setUuid(GenUtil.getGenKey(PrimaryType.ITEM));
			item.setItemCount(count);
			item.setUid(uid);
			ItemInfo iteminfo = BagUtil.CoverItemInfo(item);
			iteminfo.insert();
			bagStore.getBag(uid).addItem(item);
		}else{
			item.setItemCount(item.getItemCount() + count);
			BagUtil.CoverItemInfo(item).update();
		}
		//通知更新
		notifyUpdate(uid, item);
	}

	public BagInfo initBag(long uid) {
		BagInfo bag = new BagInfo();
		bag.setUid(uid);
		bagStore.addBag(bag);
		return bag;
	}

	@Override
	public void init() {

	}

	@Override
	public void login(long uid) {
	}

	@Override
	public void offline(long uid) {

	}

	@Override
	public void exit() {

	}

	public List<Common.ItemInfo> getBCBagItems(long uid) {
		List<Common.ItemInfo> result  = new ArrayList<>();
		List<IGoods> goods = getBagItems(uid);
		for (IGoods good : goods) {
			 //TODO		这个地方可以更具类型来转换
			Item item = (Item)good;
			Common.ItemInfo.Builder itemBuilder = Common.ItemInfo.newBuilder();
			itemBuilder.setItemCount(item.getItemCount());
			itemBuilder.setItemId(item.getItemTplId());
			result.add(itemBuilder.build());
		}
		return result;
	}
	
	/**
	 * 研发道具
	 * @param player
	 * @param itemId
	 */
	public void createYFItem(IPlayer player, int itemId) {
		SC_CreateItem.Builder scMsg = SC_CreateItem.newBuilder();
		
		//配置是否存在
		CreationTpl createTpl =  CreationTplService.getCreationTplService(itemId);
		if(createTpl == null){
			LogUtil.error("createYFItem item config is not exits.");
//			scMsg.setRet(MessageUtil.getRetInfo(AppCode.CONFIG_NOT_EXITS));
			MessageUtil.send2Client(player,scMsg.build(),AppCode.CONFIG_NOT_EXITS);
			return;
		}
		
		//判定道具是否足够
		Map<Integer,Integer> needData = createTpl.getNeed_data();
		boolean flag = checkRemoveGoods(player.getUid(), needData);
		if(!flag){
			LogUtil.error("createYFItem item is not enough.");
//			scMsg.setRet(MessageUtil.getRetInfo(AppCode.ITEM_NOT_ENOUGH));
			MessageUtil.send2Client(player,scMsg.build(),AppCode.ITEM_NOT_ENOUGH);
			return;
		}
		
		//判定闯关条件是否满足
		int needCopyLevel = createTpl.getNeed_level();
		int maxCopyLevel = ServiceManger.getService(CopyService.class).getMaxCopy(player.getUid());
		if(needCopyLevel < maxCopyLevel ){
			LogUtil.error("createYFItem item is not needCopyLevel.");
//			scMsg.setRet(MessageUtil.getRetInfo(AppCode.LEVEL_NOT_ENOUGH));
			MessageUtil.send2Client(player,scMsg.build(),AppCode.LEVEL_NOT_ENOUGH);
			return;
		}
		
		HeroOtherData heroOtherInfo = getHeroOtherDataExtend(player.getUid());
		List<Integer> createItemList =  heroOtherInfo.getCreateItemList();
		createItemList.add(itemId);
		heroOtherInfo.setCreateItemList(createItemList);
		
//		scMsg.setRet(MessageUtil.getRetInfo(AppCode.SUCCESS));
		scMsg.setItemId(itemId);
		MessageUtil.send2Client(player,scMsg.build(),AppCode.SUCCESS);
	}
	
	public HeroOtherData getHeroOtherDataExtend(long uid){
		HeroOtherData heroOtherInfo = bagStore.getOtherDatas(uid);
		if(heroOtherInfo == null){
			heroOtherInfo = new HeroOtherData();
			heroOtherInfo.setUid(uid);
			heroOtherInfo.insert();
			bagStore.addOtherDatas(heroOtherInfo);
		}
		
		return heroOtherInfo; 
	}
	
	/**
	 * 合成道具
	 * @param player
	 * @param itemId
	 */
	public void mergeItem(IPlayer player, int itemId) {
		SC_MergeItem.Builder scMsg = SC_MergeItem.newBuilder();
		long uid = player.getUid();
		
		//配置是否存在
		CompoundTpl compoundTpl = CompoundTplService.getCompoundTplService(itemId);
		if(compoundTpl == null){
			LogUtil.error("mergeItem item config is not exits.");
//			scMsg.setRet(MessageUtil.getRetInfo(AppCode.CONFIG_NOT_EXITS));
			MessageUtil.send2Client(player,scMsg.build(),AppCode.CONFIG_NOT_EXITS);
			return;
		}
		
		//判定道具是否足够
		Map<Integer,Integer> needData = compoundTpl.getNeed_data();
		boolean flag = checkRemoveGoods(player.getUid(), needData);
		if(!flag){
			LogUtil.error("createYFItem item is not enough.");
//			scMsg.setRet(MessageUtil.getRetInfo(AppCode.ITEM_NOT_ENOUGH));
			MessageUtil.send2Client(player,scMsg.build(),AppCode.ITEM_NOT_ENOUGH);
			return;
		}
		
		//判定是否可以合成 
		flag = CompoundTplService.getCanMerge().contains(itemId) ? true: getHeroOtherDataExtend(uid).getCreateItemList().contains(itemId);
		if(!flag){
			LogUtil.error("mergeItem NEED_YF_ITEM,targetId={}.",itemId);
//			scMsg.setRet(MessageUtil.getRetInfo(AppCode.NEED_YF_ITEM));
			MessageUtil.send2Client(player,scMsg.build(),AppCode.NEED_YF_ITEM);
			return;
		}
		
		removeGoods(uid, needData);
		
		addBagItem(uid, itemId, 1, LogSource.CREATE_ITEM);
		
//		scMsg.setRet(MessageUtil.getRetInfo(AppCode.SUCCESS));
		scMsg.setItemId(itemId);
		MessageUtil.send2Client(player,scMsg.build(),AppCode.SUCCESS);
	}

	@Override
	public void load(long uid) {
		this.initBag(uid);
		
		//加载背包数据
		List<ItemInfo> itemInfos = Daos.getDao().getListByCondition(ItemInfo.class,"where uid ="+uid);
		if(itemInfos != null){
			for (ItemInfo item : itemInfos) {
				bagStore.getBag(uid).addItem(BagUtil.CoverItem(item));
			}
		}
		
		//加载研发数据
		HeroOtherData heroOtherData = Daos.getDao().getObjectByCondition(HeroOtherData.class, "where uid ="+uid);
		if(heroOtherData != null){
			 bagStore.addOtherDatas(heroOtherData);
		}
	}
}
