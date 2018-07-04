package com.game.service.hero;

import com.game.constant.ModuleName;
import com.game.core.IPlayer;
import com.game.mapping.annotation.EasyMapping;
import com.game.mapping.annotation.EasyWorker;
import com.game.mapping.constant.EasyGroup;
import com.game.msg.IMessageReadable;
import com.game.msg.SCMessage;
import com.game.service.bag.service.BagService;
import com.game.util.MessageUtil;
import log.LogUtil;
import message.Bag;
import message.MessageCode;
import message.common.Common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Hero基础属性
 * @author XieWen
 * @date 2016-3-15 下午2:40:28
 */
@Controller
@EasyWorker(moduleName=ModuleName.HERO,groupName = EasyGroup.BUS_CACHE)
public class HeroAction {
	
	@Autowired
	private BagService bagService;
	
	/**
	 * 背包信息
	 */
//	@EasyMapping(mapping=MessageCode.CS_BagInfo)
//	public void getBagInfos(IMessageReadable  msg) {
//		IPlayer player = msg.getIPlayer();
//		Bag.SC_BagInfo.Builder builder = Bag.SC_BagInfo.newBuilder();
//
//		List<Common.ItemInfo> values = bagService.getBCBagItems(player.getUid());
//		builder.addAllItemInfos(values);
//		
//		player.send2Client(MessageUtil.createMessage(builder.build()));
//	}

	public void setBagService(BagService bagService) {
		this.bagService = bagService;
	}
}