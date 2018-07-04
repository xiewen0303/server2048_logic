package com.game.core;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import log.LogUtil;

import com.game.context.ServiceManger;
import com.game.core.lock.LockManager;
import com.game.msg.DispatchHandle;
import com.game.msg.IInnerReadable;
import com.game.msg.IMessageWritable;

public class PlayerForWS implements IPlayer{
	
	
	private ChannelHandlerContext ctx;
	private long uid;
	private String accountId;
	
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public void setCtx(ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}

	public ChannelHandlerContext getCtx() {
		return ctx;
	}

	public void destroy() {
		/*
		 * if (user == null){ return; }
		 * user.getUserBaseInfo().setLogoutTime(System.currentTimeMillis());
		 * user.getUserBaseInfo().update(); ctx.setAttachment(null); //TODO
		 * 设置清内存和存盘结束的状态 ctx = null; user.setPlayer(null);
		 */
	}
	
	private void sendMessage(IMessageWritable scMsg) {
		if (ctx != null) {
			ByteBuf byteBuf = scMsg.write();
			WebSocketFrame sendData =new BinaryWebSocketFrame(byteBuf);
			ctx.writeAndFlush(sendData);
		}else{
			LogUtil.error("uid="+uid+"的玩家已经离线，发送数据失败！");
		}
	}

	@Override
	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}


	
	/**
	 * 将协议发送到客户端
	 * @param scMsg
	 * @date 2016-4-15 下午5:03:15
	 */
	@Override
	public void send2Client(IMessageWritable scMsg) {
		this.sendMessage(scMsg);
	}

	/**
	 * 广播数据到客服端
	 * @param scMsg
	 * @param uids
     */
	public void broadcast2Client(IMessageWritable scMsg,long ... uids) {
		DispatchHandle.broadcast2Client(scMsg,uids);
	}

	/**
	 * 跳转到内部协议
	 * @param scMsg
	 */
	public void dispathInnerMsg(IInnerReadable scMsg) {
		//TODO
//		DispatchHandler.dispatchInner(scMsg);
//		DispatchHandle.dispathInnerMsg(scMsg, uid);
	}

	@Override
	public void offline() {
		//TODO 添加五分钟的延时操作

		//释放离线缓存
		ServiceManger.getServcieManger().offline(this.getUid());
		//释放离线玩家的锁
		LockManager.getLockManager().offline(this.getUid());
	}
	
	
	 /**
	 * 挤号操作(不清内存)
	 * @param message
	 */
	 public void sendMessageSynAndClose(IMessageWritable scMsg) {
		 if (ctx != null) {
			 ByteBuf byteBuf = scMsg.write();
			 ChannelFuture future = ctx.writeAndFlush(byteBuf);
			 
			 future.addListener(new ChannelFutureListener() {
				 @Override
				 public void operationComplete(ChannelFuture arg0) throws Exception {
					 ctx.close();
				 }
			 });
		 }
	 }
}