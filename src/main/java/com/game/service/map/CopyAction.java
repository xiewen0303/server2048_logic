package com.game.service.map;

import java.util.List;

import message.MessageCode;
import message.common.Common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.game.constant.AppCode;
import com.game.constant.ModuleName;
import com.game.core.IPlayer;
import com.game.mapping.annotation.EasyMapping;
import com.game.mapping.annotation.EasyWorker;
import com.game.mapping.constant.EasyGroup;
import com.game.msg.IMessageReadable;
import com.game.service.map.model.ChildCopy;
import com.game.service.map.service.CopyService;
import com.game.util.MessageUtil;

@Controller
@EasyWorker(moduleName=ModuleName.DUNGEON,groupName = EasyGroup.BUS_CACHE)
public class CopyAction {
	
	@Autowired
	private CopyService mapService;

//	/**
//	 * 副本信息
//	 */
//	@EasyMapping(mapping= MessageCode.CS_CopyInfo)
//	public void copyInfo(IMessageReadable  msg) {
//		IPlayer player = msg.getIPlayer();
//		
//		message.Copy.CS_CopyInfo csMsg = msg.getMessage();
//		int type = csMsg.getCopyType();
//		
//		List<ParentCopy> parentCopys = mapService.getCopyInfo(type,player.getUid());
//		message.Copy.SC_CopyInfo.Builder builder = message.Copy.SC_CopyInfo.newBuilder();
//		if(parentCopys != null){
//			for (ParentCopy parentCopy : parentCopys) {
//				message.Copy.CopyProgressInfo.Builder pross = message.Copy.CopyProgressInfo.newBuilder();
//				pross.setTplId(parentCopy.getMapTplId());
//				pross.setStar(parentCopy.getStar());
//				builder.addCopyProgressInfo(pross.build());
//			}
//		}
//		player.send2Client(MessageUtil.createMessage(builder.build()));
//	}
	
	/**
	 * 子副本信息
	 */
	@EasyMapping(mapping= MessageCode.CS_ChildCopyInfo)
	public void childCopyInfo(IMessageReadable  msg) {
		IPlayer player = msg.getIPlayer();
		
		message.Copy.CS_ChildCopyInfo csMsg = msg.getMessage();
		int parmentTplId = csMsg.getParentTplId();
		
		List<ChildCopy> childCopy = mapService.getChildCopyInfo(parmentTplId,player.getUid());
		message.Copy.SC_ChildCopyInfo.Builder builder = message.Copy.SC_ChildCopyInfo.newBuilder();
		if(childCopy != null) {
			for (ChildCopy parentCopy : childCopy) {
				message.Copy.CopyProgressInfo.Builder pross = message.Copy.CopyProgressInfo.newBuilder();
				pross.setTplId(parentCopy.getMapTplId());
				pross.setStar(parentCopy.getStar());
				builder.addCopyProgressInfo(pross.build());
			}
		}
		MessageUtil.send2Client(player,builder.build(),AppCode.SUCCESS);
	}
	
	/**
	 * 进入副本
	 */
	@EasyMapping(mapping= MessageCode.CS_EnterCopy)
	public void enterCopy(IMessageReadable  msg) {
		IPlayer player = msg.getIPlayer();
		
		message.Copy.CS_EnterCopy csMsg = msg.getMessage();
		int tplId = csMsg.getChildTplId();
		List<Common.GoodsInfo> goods = csMsg.getGoodsList();
		
		mapService.enterCopy(player,goods,tplId,player.getUid());
		
	}
	
	
	/**
	 * 完成副本
	 */
	@EasyMapping(mapping= MessageCode.CS_EnterFinish)
	public void enterFinish(IMessageReadable  msg) {
		IPlayer player = msg.getIPlayer();
		
		message.Copy.CS_EnterFinish csMsg = msg.getMessage();
		mapService.enterFinish(player,csMsg);
	}
	
	/**
	 * 完成副本
	 */
	@EasyMapping(mapping= MessageCode.CS_GetParentRewards)
	public void getParentRewards(IMessageReadable msg) {
		IPlayer player = msg.getIPlayer();
		
		message.Copy.CS_GetParentRewards csMsg = msg.getMessage();
		mapService.getParentRewards(player,csMsg);
	}
}
