package com.game.core;

import io.netty.channel.ChannelHandlerContext;

import com.game.msg.IInnerReadable;
import com.game.msg.IMessageWritable;


public interface IPlayer {
	
//	/**
//	 * 发送协议
//	 * @param scMsg
//	 */
//	public void sendMessage(IMessageWritable scMsg);

	/**
	 * 将协议发送到客户端
	 * @param scMsg
	 * @date 2016-4-15 下午5:03:15
	 */
	public void send2Client(IMessageWritable scMsg);

	/**
	 * 广播数据到客服端
	 * @param scMsg
	 * @param uids
	 */
	public void broadcast2Client(IMessageWritable scMsg,long ... uids);

	/**
	 * 跳转到内部协议
	 * @param scMsg
	 */
	public void dispathInnerMsg(IInnerReadable scMsg);
	
	/**
	 * 玩家uid
	 * @return 
	 */
	public long getUid();


	public void setUid(long uid);

	void offline();

	public void setCtx(ChannelHandlerContext ctx);

	public void sendMessageSynAndClose(IMessageWritable createMessage);
	
	public String getAccountId();

	public void setAccountId(String accountId);
}
