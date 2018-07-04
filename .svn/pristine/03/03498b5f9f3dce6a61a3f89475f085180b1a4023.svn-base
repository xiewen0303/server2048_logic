package com.game.service.map.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import log.LogUtil;
import message.Copy.CS_EnterFinish;
import message.Copy.CS_GetParentRewards;
import message.Copy.SC_EnterFinish;
import message.Copy.SC_GetParentRewards;
import message.common.Common;
import message.common.Common.ItemInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.config.tpl.MapBaseTpl;
import com.game.config.tpl.MapBaseTplService;
import com.game.config.tpl.MapChildTpl;
import com.game.config.tpl.MapChildTplService;
import com.game.constant.AppCode;
import com.game.constant.GlobalConst;
import com.game.context.Daos;
import com.game.context.ServiceManger;
import com.game.core.AbstractService;
import com.game.core.IPlayer;
import com.game.service.bag.model.LogSource;
import com.game.service.bag.service.BagService;
import com.game.service.map.CopyStore;
import com.game.service.map.model.ChildCopy;
import com.game.service.map.model.Copy;
import com.game.service.map.model.ParentCopy;
import com.game.service.map.util.CopyUtil;
import com.game.util.MessageUtil;
import com.game.util.ToolUtils;

import db.ibatis.bean.CopyInfo;

@Service
public class CopyService extends AbstractService {
	
	@Autowired
	private CopyStore mapStore;
	
    @Override
    public void init() {
    }
    
    @Override
	public void load(long uid) {
    	List<CopyInfo> copyInfos = Daos.getDao().getListByCondition(CopyInfo.class,"where uid="+uid);
    	if(ToolUtils.isEmpty(copyInfos)){
    		initCopyInfo(uid);
    	}else{
    		//DB store
    		getCopyInfo(uid); 
    	}
	}

	public void initCopyInfo(long uid){
    	//创建号的时候
    	Copy copy = new Copy();
    	ParentCopy p = new ParentCopy();
    	p.setMapTplId(1);
    	p.setState(2);
    	
    	ChildCopy c = new ChildCopy();
    	c.setMapTplId(1);
    	c.setStar(0);
    	p.addChildCopy(c);
    	
    	copy.addParentCopy(p);
    	copy.setMaxCopyTplId(1);
    	
    	mapStore.addCopyInfo(uid,copy);
    	
    	//DB store
    	CopyUtil.cover2CopyInfo(uid,p).insert();
    }

    @Override
    public void login(long uid) {
    	//处理需要推送给客服端的信息
    	
    }
    
    @Override
    public void offline(long uid) {
    }

    @Override
    public void exit() {

    }
    
    public Copy getCopyInfo(long uid){
    	Copy copyInfo = mapStore.getCopyInfo(uid);
    	if(copyInfo != null) {
    		return copyInfo;
    	}
    	List<CopyInfo> copyInfos = Daos.getDao().getListByCondition(CopyInfo.class, "where uid="+uid);
    	if(copyInfos == null){
    		initCopyInfo(uid);
    		return mapStore.getCopyInfo(uid);
    	}
    	copyInfo = new Copy();
    	Map<Integer,ParentCopy> parentCopys = new HashMap<>();
    	
    	
    	
    	for (CopyInfo c : copyInfos) {
    		ParentCopy parentCopy = CopyUtil.cover2ParentCopy(c);
    		parentCopys.put(c.getMapTplId(),parentCopy);
    		
    		for (ChildCopy childCopy : parentCopy.getChildCopys().values()) {
    			if (copyInfo.getMaxCopyTplId() < childCopy.getMapTplId()){
        			copyInfo.setMaxCopyTplId(childCopy.getMapTplId());
        		}
			}
    		
		}
    	
    	copyInfo.setParentCopys(parentCopys);
    	mapStore.addCopyInfo(uid,copyInfo);
    	return copyInfo;
    }

	public List<ParentCopy> getParentCopys(long uid) {
		Copy copyInfo = getCopyInfo(uid);
		return new ArrayList<>(copyInfo.getParentCopys().values()) ;
	}
	
	public ParentCopy getCopyParentInfo(long uid,int parmentTplId){
		Copy copyInfo = getCopyInfo(uid);
		for (ParentCopy parentCopy : copyInfo.getParentCopys().values()){
			if (parentCopy.getMapTplId() == parmentTplId){
				return parentCopy;
			}
		}
		return null;
	}
	
	//获得
	public List<ChildCopy> getChildCopyInfo(int parentTplId, long uid) {
		ParentCopy parentCopy = getCopyParentInfo(uid,parentTplId);
		if (parentCopy == null){
			LogUtil.error("获取父地图错误，uid:"+uid+",parentTplId:"+parentTplId);
			return null;
		}
		return new ArrayList<>(parentCopy.getChildCopys().values());
	}
	
	public void enterNewbieCopy(IPlayer player,List<Common.GoodsInfo> goodsInfos, int childTplId, long uid){
		message.Copy.SC_EnterCopy.Builder builder = message.Copy.SC_EnterCopy.newBuilder();
		builder.setChildTplId(childTplId);
		MessageUtil.send2Client(player, builder.build(), AppCode.SUCCESS);
	}
	
	
	/**
	 * 进入地图,分配地图
	 * @param goods
	 * @param childTplId
	 * @param uid
	 * @return
	 */
	public void enterCopy(IPlayer player,List<Common.GoodsInfo> goodsInfos, int childTplId, long uid) {
		if(CopyUtil.isNewbieCopy(childTplId)){
			enterNewbieCopy(player, goodsInfos, childTplId, uid);
			return ;
		}
		
		message.Copy.SC_EnterCopy.Builder builder = message.Copy.SC_EnterCopy.newBuilder();
		
		Map<Integer,Integer> targetItems = new HashMap<>();
		
		//判定道具是否存在
		for (Common.GoodsInfo goodsInfo : goodsInfos){
			int tplId = goodsInfo.getTplId();
			int count = 1;//goodsInfo.getSlot();
			
			Integer resCount = targetItems.get(tplId);
			if(resCount != null){
				count += resCount;
			}
			targetItems.put(tplId, count);
		}
		
		BagService bagService = ServiceManger.getService(BagService.class);
		boolean flag = bagService.checkRemoveGoods(uid, targetItems);
		if(!flag){
//			builder.setRet(MessageUtil.getRetInfo(AppCode.ITEM_NOT_ENOUGH));
			MessageUtil.send2Client(player,builder.build(),AppCode.ITEM_NOT_ENOUGH);
			return;
		}
		
		bagService.removeGoods(uid, targetItems);
		
		
		//获取配置表
		MapChildTpl childMapTpl = MapChildTplService.getMapChildTpl(childTplId);
		if(childMapTpl == null){
			LogUtil.error("map tpl is not exits,childTplId:"+childTplId);
//			builder.setRet(MessageUtil.getRetInfo(AppCode.CONFIG_NOT_EXITS));
			MessageUtil.send2Client(player,builder.build(),AppCode.CONFIG_NOT_EXITS);
			return ;
		}
		
		Map<Integer,Integer> initData =  childMapTpl.getInit_monster_item();
		if (ToolUtils.isEmpty(initData)){
			LogUtil.error("child map tpl is empty,Init_monster_item:"+initData);
//			builder.setRet(MessageUtil.getRetInfo(AppCode.CONFIG_NOT_EXITS));
			MessageUtil.send2Client(player,builder.build(),AppCode.CONFIG_NOT_EXITS);
			return ;
		}
		
		//判定当前关卡是否开启
		int maxCopyId = getMaxCopy(uid);
		if(childTplId > maxCopyId){
//			builder.setRet(MessageUtil.getRetInfo(AppCode.LEVEL_NOT_ENOUGH));
			LogUtil.error("enterCopy LEVEL_NOT_ENOUGH targetId:{},selfMaxId:{}",childTplId,maxCopyId);
			MessageUtil.send2Client(player,builder.build(),AppCode.LEVEL_NOT_ENOUGH);
			return;
		}
		
//		List<CellInfo> result = new ArrayList<>();
//		
//		List<Integer> slots = new ArrayList<>();
//		for(int i = 1;i <= GlobalConst.SLOT_MAX;i++){
//			slots.add(i);
//		}
//		
//		for(Entry<Integer, Integer> entry : initData.entrySet()){
//			int tplId = entry.getKey();
//			int count = entry.getValue();
//			for(int i = 0;i<count;i++){
//				CellInfo.Builder cellInfo = CellInfo.newBuilder();
//				cellInfo.setTplId(tplId);
//				
//				//判定占用几个格子位
//				if(tplId > GlobalConst.MONSTER_START_ID) {
//					MonsterTpl  monsterTpl =  MonsterTplService.getMonsterTpl(tplId);
//					if(monsterTpl == null) {
//						LogUtil.error("monsterTpl is null,tplId :"+tplId);
//						continue;
//					}
//					
//					int gridType = monsterTpl.getUse_grid_count();
//					
//					// 不需要分组
//					if(gridType == 1){
//						int index = RandomUtil.getRandom(slots.size());
//						int slot = slots.remove(index);
//						cellInfo.addSlots(slot);
//						result.add(cellInfo.build());
//						continue;
//					}
//					
//					//需要分组的
//					List<int[]> tarSlots = getSlotGroups(slots, gridType);
//					if(tarSlots == null) {
//						LogUtil.error("tarSlot is null:"+JSONArray.toJSONString(slots), gridType);
//						continue;
//					}
//					int tarIndex = RandomUtil.getRandom(tarSlots.size());
//					int[] tarResultSlots = tarSlots.remove(tarIndex);
//					for (Integer realSlot : tarResultSlots) {
//						 boolean reFlag = slots.remove(realSlot);
//						 if(!reFlag){
//							 LogUtil.error("slots remove realSlot false:"+JSONArray.toJSONString(slots), realSlot);
//							 continue;
//						 }
//						 cellInfo.addSlots(realSlot);
//					}
//					result.add(cellInfo.build());
//					
//				}else{
//					int index = RandomUtil.getRandom(slots.size());
//					int slot = slots.remove(index);
//					cellInfo.addSlots(slot);
//					result.add(cellInfo.build());
//				}
////				if(cellInfo.getSlotsCount() == 0){
////					LogUtil.error("cellInfo slotsCount is 0");
////					continue;
////				}
//			}
//		}
//		builder.addAllCellInfos(cellInfos);
		
		builder.setChildTplId(childTplId);
		MessageUtil.send2Client(player, builder.build(), AppCode.SUCCESS);
	}
	
	/**
	 * 返回可以随机的格子号数组
	 * @param slots
	 * @param gridType
	 * @return
	 */
	public List<int[]> getSlotGroups(List<Integer> slots,int gridType){
		List<int[]> result = null;
		switch (gridType) {
//		case 1:
//			// 不需要分组
//			break;
		case 2: //横着占用两个格子
			result = getRandomWData(slots);
			break;
		case 3: //竖着占用两个格子
			result = getRandomHData(slots);
			break;
		case 4: //占用4个格子
			//TODO
			LogUtil.debug("TODO type 4");
			break;

		default:
			LogUtil.error("type is not exits,type=" + gridType);
			break;
		}
		
		return result;
	}
	
	/**
	 * 横着占用两个格子
	 */
	public final static int SLOTS_W = 2;
	/**
	 * 竖着占用两个格子
	 */
	public final static int SLOTS_H = 2;
	
	/**
	 * 获得可以随机横着的数组
	 * @return
	 */
	public List<int[]> getRandomWData(List<Integer> slots){
		List<int[]> result = new ArrayList<>();
		int wIndex = GlobalConst.SLOT_WIDTH / SLOTS_W;
		
		for(int y=1;y<=GlobalConst.SLOT_HEIGHT ;y++){
			for (int x = 0; x < wIndex; x++ ) {
				boolean flag = true;
				
				int[] data = new int[SLOTS_W];
				for(int t = 1;t<= SLOTS_W;t++) {
					int targetX = x*SLOTS_W + t;
					int targetIndex = getSlotByXY(y, targetX);
					if(!slots.contains(targetIndex)){
						flag = false;
						break;
					}
					data[t-1] = targetIndex;
				}
				
				//存入可随机的数组中
				if(flag){
					result.add(data);
				}
			}
		}
		return result;
	}
	
	
	/**
	 * 获得可以随机竖着的数组
	 * @return
	 */
	public List<int[]> getRandomHData(List<Integer> slots){
		List<int[]> result = new ArrayList<>();
		int hIndex = GlobalConst.SLOT_HEIGHT / SLOTS_H;
		
		for(int x = 1; x <= GlobalConst.SLOT_WIDTH; x++){
			for (int y = 0; y < hIndex; y++ ) {
				boolean flag = true;
				
				int[] data = new int[SLOTS_H];
				for(int t = 1; t <= SLOTS_H; t++) {
					int targetY = y*SLOTS_H + t;
					int targetIndex = getSlotByXY(targetY, x);
					if(!slots.contains(targetIndex)){
						flag = false;
						break;
					}
					data[t-1] = targetIndex;
				}
				
				//存入可随机的数组中
				if(flag){
					result.add(data);
				}
			}
		}
		return result;
	}
	
	/**
	 * 
	 *<b>坐标---------->格子</b>
	 *<br/>
	 * (y,x)<br/>
	 * ---------------------------------->x<br/>
	 * |(1,1)|(1,2)|...|(1,6)|	<br/>
	 *-----------------------   <br/>
	 * |(2,1)|(2,2)|...|(2,6)| 	<br/>
	 * -------------------------<br/>
	 * |(3,1)|(3,2)|...|(3,6)|  <br/>
	 * -------------------------<br/>
	 * |(4,1)|(4,2)|...|(4,6)|  <br/>
	 * -------------------------<br/>
	 * |(5,1)|(5,2)|...|(5,6)|  <br/>
	 * -------------------------<br/>
	 * |(6,1)|(6,2)|...|(6,6)|  <br/>
	 * -------------------------<br/>
	 * |
	 * Y
	 * @param y
	 * @param x
	 * @return
	 */
	public int getSlotByXY(int y,int x) {
		if(x<=0|| y<=0 || x> GlobalConst.SLOT_WIDTH || y > GlobalConst.SLOT_HEIGHT){
			LogUtil.error("enter point error,x:"+x+"\ty:"+y);
			return 0;
		}
		return (y-1)*GlobalConst.SLOT_WIDTH+x;
	}
	
	/**
	 * 格子---------->坐标
	 * @param slot
	 * @return [y,x]
	 */
	public int[] getXYBySlot(int slot) {
		if(slot > GlobalConst.SLOT_MAX || slot <= 0){
			LogUtil.error("slot is error,slot:"+slot);
			return null;
		}
		int x = slot%GlobalConst.SLOT_WIDTH;
		int y = slot /GlobalConst.SLOT_WIDTH+1;
		
		if(x == 0){
			x = GlobalConst.SLOT_WIDTH;
			--y;
		}
		
		return new int[]{y,x};
	}

	public void enterFinish(IPlayer player, CS_EnterFinish csMsg) {
		SC_EnterFinish.Builder scMsg = SC_EnterFinish.newBuilder();
		int tplId = csMsg.getChildTplId();
		int star = csMsg.getStar();
		int state = csMsg.getState();
		long uid = player.getUid();
		List<ItemInfo> items = csMsg.getGoodsList();
		
		if(CopyUtil.isNewbieCopy(tplId)){
			finishNewbieCopy(player, tplId, star, uid, state,items);
			return ;
		}
		
		Copy copy = getCopyInfo(player.getUid());
		
		if(items != null &&  !items.isEmpty()){
			
			Map<Integer,Integer> datas = new HashMap<>();
			for (ItemInfo itemInfo : items) {
				Integer count = datas.get(itemInfo.getItemId());
				count = count!=null ?count+itemInfo.getItemCount():itemInfo.getItemCount();
				datas.put(itemInfo.getItemId(), count);
			}
			
			BagService bagService = ServiceManger.getService(BagService.class);
			bagService.addBagItems(player.getUid(), datas, LogSource.FINISH_COPY);
		}
		
		ChildCopy childCopy = getChildCopy(player.getUid(), tplId);
		if(childCopy == null){
			LogUtil.error("childCopy is null,childTplId="+tplId);
//			scMsg.setRet(MessageUtil.getRetInfo(AppCode.CONFIG_NOT_EXITS));
			MessageUtil.send2Client(player,scMsg.build(),AppCode.CONFIG_NOT_EXITS);
			return;
		}
		if(star > childCopy.getStar()){
			childCopy.setStar(star);
			
			if(tplId == copy.getMaxCopyTplId()){
				//解锁下一关卡
				unlockNext(player.getUid(),copy,tplId + 1,scMsg);
			}
			
			ParentCopy parentCopy = getParentCopy(uid, tplId);
			if(star >= 3 ){
				List<MapChildTpl> mapChildTpls = MapChildTplService.getMapChildTpls(parentCopy.getMapTplId());
				if(ToolUtils.isEmpty(mapChildTpls)){
					boolean f = true;
					for (MapChildTpl mapChildTpl : mapChildTpls) {
						ChildCopy c = getChildCopy(uid, mapChildTpl.getId());
						if(c == null || c.getStar() < 3){
							f = false;
							break;
						}
					}	
					
					//如果都超过了三星,更新父类状态为可领奖
					if(f && state == 2){
						parentCopy.setState(1);
						
						Common.ParentInfo.Builder pinfo = Common.ParentInfo.newBuilder();
				    	pinfo.setState(1);
				    	pinfo.setTplId(parentCopy.getMapTplId());
				    	scMsg.addParentInfos(pinfo);
					}
				}
			}
			
			CopyUtil.cover2CopyInfo(uid, parentCopy).update();
		}
		
		scMsg.setChildTplId(tplId);
		scMsg.setStar(star);
		scMsg.setState(state);
//		scMsg.setRet(MessageUtil.getRetInfo(AppCode.SUCCESS));
		MessageUtil.send2Client(player,scMsg.build(),AppCode.SUCCESS);
	}
	
	private void finishNewbieCopy(IPlayer player, int tplId,int star, long uid ,int state,List<ItemInfo> items) {
		
		Map<Integer,Integer> datas = new HashMap<>();
		for (ItemInfo itemInfo : items) {
			Integer count = datas.get(itemInfo.getItemId());
			count = count!=null ?count+itemInfo.getItemCount():itemInfo.getItemCount();
			datas.put(itemInfo.getItemId(), count);
		}
		
		BagService bagService = ServiceManger.getService(BagService.class);
		bagService.addBagItems(player.getUid(), datas, LogSource.FINISH_COPY);
		
		
		
		SC_EnterFinish.Builder scMsg = SC_EnterFinish.newBuilder();
		scMsg.setChildTplId(tplId);
		scMsg.setStar(star);
		scMsg.setState(state);
//		scMsg.setRet(MessageUtil.getRetInfo(AppCode.SUCCESS));
		MessageUtil.send2Client(player,scMsg.build(),AppCode.SUCCESS);
	}

	/**
	 * 解锁新关卡
	 * @param childTplId
	 */
	public void unlockNext(long uid,Copy copy,int unlockChildTplId,SC_EnterFinish.Builder scMsg){
		MapChildTpl unlockMapTpl = MapChildTplService.getMapChildTpl(unlockChildTplId);
		if(unlockMapTpl == null){
			return;
		}
		
		MapChildTpl mapChildTpl = MapChildTplService.getMapChildTpl(copy.getMaxCopyTplId());
		
		
		ChildCopy newChild = new ChildCopy();
		newChild.setMapTplId(unlockMapTpl.getId());
		newChild.setStar(0);
		
		//开启新的父类关卡
		if(unlockMapTpl.getMapId() > mapChildTpl.getMapId()){
			ParentCopy parentCopy = new ParentCopy();
			parentCopy.setMapTplId(unlockMapTpl.getMapId());
			parentCopy.setState(2);
			
	    	parentCopy.addChildCopy(newChild);
	    	copy.addParentCopy(parentCopy);
	    	
	    	CopyUtil.cover2CopyInfo(uid, parentCopy).insert();
	    	
	    	Common.ParentInfo.Builder pinfo = Common.ParentInfo.newBuilder();
	    	pinfo.setState(2);
	    	pinfo.setTplId(unlockMapTpl.getMapId());
	    	scMsg.addParentInfos(pinfo);
		}else{
			ParentCopy parentCopy = getParentCopy(uid, unlockMapTpl.getId());
			parentCopy.addChildCopy(newChild);
		}
		
		copy.setMaxCopyTplId(unlockMapTpl.getId());
		scMsg.setUnlockChildTplId(unlockMapTpl.getId());
	}
	
	public ParentCopy getParentCopy(long uid,int childTplId){
		
		MapChildTpl mapChildTpl = MapChildTplService.getMapChildTpl(childTplId);
		if(mapChildTpl == null){
			LogUtil.error("getParentCopy mapChildTpl is empty,tplId="+childTplId);
			return null;
		}
		
		Copy copy = getCopyInfo(uid);
		return	copy.getParentCopys().get(mapChildTpl.getMapId());
	}
	
	public ChildCopy getChildCopy(long uid,int childTplId){
		
		MapChildTpl mapChildTpl = MapChildTplService.getMapChildTpl(childTplId);
		if(mapChildTpl == null){
			LogUtil.error("getParentCopy mapChildTpl is empty,tplId="+childTplId);
			return null;
		}
		
		Copy copy = getCopyInfo(uid);
		ParentCopy parentCopy = copy.getParentCopys().get(mapChildTpl.getMapId());
		
		if(parentCopy == null){
			LogUtil.error("通关的关卡不存在，childtplId={}",childTplId);
			return null;
		}
		
		return parentCopy.getChildCopys().get(childTplId);
	}
	
	/**
	 * 获取地图最高的Id
	 * @param uid
	 * @return
	 */
	public int getMaxCopy(long uid) {
		Copy copy = getCopyInfo(uid);
		int maxCopyId = copy.getMaxCopyTplId();
		MapChildTpl mapChildtpl = MapChildTplService.getMapChildTpl(maxCopyId);
		
		if(mapChildtpl == null){
			 return 1;
		}
		return mapChildtpl.getId();
	}
	
	/**
	 * 领取奖励
	 * @param player
	 * @param csMsg
	 */
	public void getParentRewards(IPlayer player, CS_GetParentRewards csMsg) {
		SC_GetParentRewards.Builder scMsg = SC_GetParentRewards.newBuilder();
		int tplId = csMsg.getTplId();
		long uid = player.getUid();
		
		ParentCopy parentCopy = getParentCopy(uid, tplId);
		if(parentCopy == null){
			//请先通关
			LogUtil.error("getParentRewards LEVEL_NOT_ENOUGH ");
			MessageUtil.send2Client(player, scMsg.build(),AppCode.LEVEL_NOT_ENOUGH);
			return;
		}
		
		if(parentCopy.getState() != 1){
			//状态不对
			LogUtil.error("getParentRewards AWARD_STATE_ERROR ");
			MessageUtil.send2Client(player, scMsg.build(),AppCode.AWARD_STATE_ERROR);
			return;
		}
		
		MapBaseTpl mapBaseTpl = MapBaseTplService.getItemTpl(tplId);
		if(mapBaseTpl == null){
			LogUtil.error("getParentRewards CONFIG_NOT_EXITS ");
			MessageUtil.send2Client(player, scMsg.build(),AppCode.CONFIG_NOT_EXITS);
			return ;
		}
		
		ServiceManger.getService(BagService.class).addBagItems(uid, mapBaseTpl.getStar3_reward(), LogSource.COPY_STAR_AWARD);
		
		parentCopy.setState(3);

		scMsg.setTplId(tplId);
		MessageUtil.send2Client(player, scMsg.build(),AppCode.SUCCESS);
	}
	 
}