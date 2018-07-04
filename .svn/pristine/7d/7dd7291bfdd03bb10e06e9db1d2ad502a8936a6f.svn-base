package com.game.service.bag;

import message.Bag;
import message.MessageCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.game.constant.ModuleName;
import com.game.core.IPlayer;
import com.game.mapping.annotation.EasyMapping;
import com.game.mapping.annotation.EasyWorker;
import com.game.mapping.constant.EasyGroup;
import com.game.msg.IMessageReadable;
import com.game.service.bag.service.BagService;

@Controller
@EasyWorker(moduleName=ModuleName.BAG,groupName = EasyGroup.BUS_CACHE)
public class BagAction {
	
	@Autowired
	private BagService bagService;

	/**
	 * 研发
	 */
	@EasyMapping(mapping= MessageCode.CS_CreateItem)
	public void createYFItem(IMessageReadable  msg) {
		IPlayer player = msg.getIPlayer();
		
		Bag.CS_CreateItem csMsg = msg.getMessage();
		
		bagService.createYFItem(player,csMsg.getItemId());
	}
	
	
	/**
	 * 合成
	 */
	@EasyMapping(mapping= MessageCode.CS_MergeItem)
	public void mergeItem(IMessageReadable  msg) {
		IPlayer player = msg.getIPlayer();
		
		Bag.CS_MergeItem csMsg = msg.getMessage();
		
		bagService.mergeItem(player,csMsg.getItemId());
	}
}
