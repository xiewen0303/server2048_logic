package com.game.msg;

import log.LogUtil;

import org.springframework.context.ApplicationContext;

import com.game.context.SpringHandler;
import com.game.core.IPlayer;
import com.game.kernel.dispatch.BusinessDispatch;
import com.game.kernel.dispatch.CommonDispatch;
import com.game.kernel.dispatch.IDispatch;
import com.game.mapping.constant.EasyGroup;
import com.game.mapping.manage.CmdGroupInfo;
import com.game.service.hero.util.HeroUtil;
import com.google.protobuf.GeneratedMessage;

public class DispatchHandle {
	
	public static void dispatch(IMessageReadable csMsg){
		try {
			short cmd =  csMsg.getMessageCode();
			
			EasyGroup groupName = CmdGroupInfo.getCmdGroup(cmd);
			//通过groupName获得对应的执行器
			IDispatch dispatch = null;
			Object groupInfo = null;
			switch (groupName) {
			case BUS_CACHE:
				dispatch = getIDispatch(BusinessDispatch.class);
				groupInfo = csMsg.getUid();
				break;
				
			case LOGING:
				dispatch = getIDispatch(CommonDispatch.class);
				groupInfo = EasyGroup.LOGING.getGroupName();
				break;

			default:
				LogUtil.error("未知的分组或协议号：msgCode:"+cmd+"  groupName:"+groupName);
			}
			if (dispatch != null){
				dispatch.dispatch(groupInfo,csMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static <T extends IDispatch> T getIDispatch(Class<T> c){
		ApplicationContext context = SpringHandler.loadSpring().getSpringContext();
		return  context.getBean(c);
	}

	public static void broadcast2Client(IMessageWritable scMsg,long ... uids) {
		for (long uid : uids) {
			IPlayer player = HeroUtil.getPlayer(uid);
			if (player == null){
				continue;
			}
			player.send2Client(scMsg);
		}
	}

//	/**
//	 * 跳转到内部协议
//	 * @param scMsg
//     */
//	public static void dispathInnerMsg(IInnerReadable scMsg,long uid) {
//		IPlayer player = HeroUtil.getPlayer(uid);
//		if(player == null){
//			return;
//		}
//		player.dispathInnerMsg(scMsg);
//	}
}