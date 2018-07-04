package com.game.util;

import java.util.Arrays;

import message.MessageCode;
import message.common.Common;

import com.game.context.Stores;
import com.game.core.IPlayer;
import com.game.msg.IMessageWritable;
import com.game.msg.SCMessage;
import com.game.msg.SCMessageWS;
import com.game.service.login.LoginStore;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessageV3;

/**
 * Created by xiewen on 2016/7/26.
 */
public class MessageUtil {

    public static Common.RetInfo getRetInfo(int codeId,String ... datas){
        Common.RetInfo.Builder retInfo = Common.RetInfo.newBuilder();
        retInfo.setCodeId(codeId);
        retInfo.addAllDatas(Arrays.asList(datas));
        return retInfo.build();
    }

//	private static IMessageWritable createMessage(GeneratedMessage scMsg) {
//		String codeName = scMsg.getClass().getName();
//		short code = MessageCode.getCode(codeName);
//		return new SCMessage(code, scMsg);
//	}
	
	private static IMessageWritable createMessage(GeneratedMessageV3 scMsg,int appCode) {
		String codeName = scMsg.getClass().getName();
		short code = MessageCode.getCode(codeName);
		return new SCMessageWS(code, scMsg,appCode);
	}
	
	public static void send2Client(IPlayer player,GeneratedMessageV3 scMsg,int appCode){
		player.send2Client(createMessage(scMsg,appCode));
	}
	
	public static void send2Client(long uid,GeneratedMessageV3 scMsg,int appCode){
		IPlayer player = Stores.getStore(LoginStore.class).getIPlayer(uid);
		player.send2Client(createMessage(scMsg,appCode));
	}

	public static void sendMessageSynAndClose(IPlayer player,GeneratedMessageV3 scMsg,int appCode){
		player.sendMessageSynAndClose(createMessage(scMsg,appCode));
	}
}
