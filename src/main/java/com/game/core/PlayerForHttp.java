package com.game.core;

import io.netty.channel.ChannelHandlerContext;

import java.util.List;

import log.LogUtil;

import com.alibaba.fastjson.JSONObject;
import com.game.msg.IInnerReadable;
import com.game.msg.IMessageWritable;
import com.game.nio.http.bak.SendUtil;
import com.game.service.hero.bean.Hero;

public class PlayerForHttp implements IPlayer{
	
	private ChannelHandlerContext ctx; 
	
	private String sign;// 登录uid 
	
	private Hero hero;
	
	public PlayerForHttp(ChannelHandlerContext ctx, String sign) {
		super();
		this.ctx = ctx;
		this.sign = sign;
	}

	public ChannelHandlerContext getCtx() {
		return ctx;
	}

	public void setCtx(ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
//	
//	public void sendMessage(IMessageWritable message)  {
//		if(ctx == null) {
//			LogUtil.error("[sign="+sign+"]的玩家离线，消息发送失败！");
//			return;	
//		}
//		String accountId = "";
//		if(hero != null) {
//			accountId = this.hero.getNickName();
//		}
//		LogUtil.debug("=========玩家Id:"+accountId+"========Send协议名["+sign+"]============["+message.getClass().getSimpleName()+"发送时间："+System.currentTimeMillis()+"----]====msgContent:"+JSONObject.toJSONString(message));
//	 	
////		//发送需要心跳发送的数据
////	 	List<IMessageWritable> needMessages = HeartBeatUtil.getHeartBeatMsgs(hero);
////	 	if(needMessages!=null && needMessages.size()>0){
////	 		needMessages.add(0,message);
////	 		SendUtil.send(ctx, needMessages);
////	 	}else{
//	 		SendUtil.sendOne(ctx,message);
////	 	}
//	}

	public Hero getHero() {
		return hero;
	}
	
	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public void closeConnection(){
		
		if(ctx == null) {
			LogUtil.error("[sign="+sign+"]的玩家离线，消息发送失败！");
			return;	
		}
		SendUtil.sendOne(ctx, null);
	}
	
	
	public void sendMessage(String msg) {
		if(ctx == null) {
			LogUtil.error("[sign="+sign+"]的玩家离线，消息发送失败！");
			return;	
		}
		LogUtil.debug("=================Send协议["+msg+"]");
		SendUtil.sendString(ctx,msg);
	}
	
	
	public void sendMessage(List<IMessageWritable> scMsgs) {
		if(ctx == null) {
			LogUtil.error("[sign="+sign+"]的玩家离线，消息发送失败！");
			return;	
		}
		for (IMessageWritable msg : scMsgs) {
			LogUtil.debug("多个协议--------Send协议["+msg+"]");
		}
		SendUtil.send(ctx, scMsgs);
	}
	
	@Override
	public long getUid() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setUid(long uid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void offline() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void send2Client(IMessageWritable scMsg) {
		if(ctx == null) {
			LogUtil.error("[sign="+sign+"]的玩家离线，消息发送失败！");
			return;	
		}
		String accountId = "";
		if(hero != null) {
			accountId = this.hero.getNickName();
		}
		LogUtil.debug("=========玩家Id:"+accountId+"========Send协议名["+sign+"]============["+scMsg.getClass().getSimpleName()+"发送时间："+System.currentTimeMillis()+"----]====msgContent:"+JSONObject.toJSONString(scMsg));
	 	
//		//发送需要心跳发送的数据
//	 	List<IMessageWritable> needMessages = HeartBeatUtil.getHeartBeatMsgs(hero);
//	 	if(needMessages!=null && needMessages.size()>0){
//	 		needMessages.add(0,message);
//	 		SendUtil.send(ctx, needMessages);
//	 	}else{
	 		SendUtil.sendOne(ctx,scMsg);
//	 	}
		
	}

	@Override
	public void broadcast2Client(IMessageWritable scMsg, long... uids) {
		// TODO Auto-generated method stub
		//短连接只能添加到队列中，等待客服端的心跳请求了
	}

	@Override
	public void dispathInnerMsg(IInnerReadable innerMsg) {
		//TODO
//		DispatchHandler.dispatchInner(innerMsg);
	}

	@Override
	public void sendMessageSynAndClose(IMessageWritable createMessage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAccountId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAccountId(String accountId) {
		// TODO Auto-generated method stub
		
	}
}
