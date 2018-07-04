
package com.game.nio.http.bak;

import com.game.msg.CSMessageHttp;
import com.game.msg.IMessageReadable;
import com.google.protobuf.GeneratedMessage;
import log.LogUtil;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * 自动生成
 */
public class MessageCode2 {

	public final static short CS_ChatMessage = 103;
	public final static short SC_ChatMessage = 104;
	public final static short CS_Login = 105;
	public final static short SC_Login = 106;
	public final static short CS_BagInfo = 107;
	public final static short SC_BagInfo = 108;
	public final static short SC_UpdateItemInfos = 109;
	public final static short CS_CopyInfo = 112;
	public final static short SC_CopyInfo = 113;
	public final static short CS_ChildCopyInfo = 114;
	public final static short SC_ChildCopyInfo = 115;
	public final static short CS_EnterCopy = 116;
	public final static short SC_EnterCopy = 117;

	public static Map<String,Short> nameCodes = new HashMap<>();

	static {
	    nameCodes.put("message.Chat$CS_ChatMessage",CS_ChatMessage);
	    nameCodes.put("message.Chat$SC_ChatMessage",SC_ChatMessage);
	    nameCodes.put("message.Login$CS_Login",CS_Login);
	    nameCodes.put("message.Login$SC_Login",SC_Login);
	    nameCodes.put("message.Bag$CS_BagInfo",CS_BagInfo);
	    nameCodes.put("message.Bag$SC_BagInfo",SC_BagInfo);
	    nameCodes.put("message.Bag$SC_UpdateItemInfos",SC_UpdateItemInfos);
	    nameCodes.put("message.Copy$CS_CopyInfo",CS_CopyInfo);
	    nameCodes.put("message.Copy$SC_CopyInfo",SC_CopyInfo);
	    nameCodes.put("message.Copy$CS_ChildCopyInfo",CS_ChildCopyInfo);
	    nameCodes.put("message.Copy$SC_ChildCopyInfo",SC_ChildCopyInfo);
	    nameCodes.put("message.Copy$CS_EnterCopy",CS_EnterCopy);
	    nameCodes.put("message.Copy$SC_EnterCopy",SC_EnterCopy);
	}

	public static short getCode(String codeName) {
		return nameCodes.get(codeName);
	}


//
//    /**
//	 * 通过协议号解析成协议对象
//	 * @param cmd   协议号
//	 * @param data  协议二进制数据
//     * @return      协议对象
//     */
//	public static GeneratedMessage getGenMessage(short cmd, byte[] data) {
//		try {
//			switch (cmd) {
//                case CS_ChatMessage:
//                	return Chat.CS_ChatMessage.parseFrom(data);
//                case CS_Login:
//                	return Login.CS_Login.parseFrom(data);
//                case CS_BagInfo:
//                	return Bag.CS_BagInfo.parseFrom(data);
//                case CS_CopyInfo:
//                	return Copy.CS_CopyInfo.parseFrom(data);
//                case CS_ChildCopyInfo:
//                	return Copy.CS_ChildCopyInfo.parseFrom(data);
//                case CS_EnterCopy:
//                	return Copy.CS_EnterCopy.parseFrom(data);
//				default:
//					LogUtil.error("message cmd is not exist,cmd:"+cmd);
//					break;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	
	/**
	 * 通过协议号解析成协议对象  (http)
	 * @param cmd   协议号
	 * @param data  协议二进制数据
     * @return      协议对象
     */
	public static IMessageReadable getGenMessage2(int cmd, ByteBuffer data) {
		try {
			switch (cmd) {
                //TODO 这个根据具体的实现
			case CS_ChatMessage:
				return new CSMessageHttp(cmd,data); 
				default:
					LogUtil.error("message cmd is not exist,cmd:"+cmd);
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}