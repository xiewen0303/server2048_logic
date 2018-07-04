package com.game.core;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import log.LogUtil;

import com.game.context.ServiceManger;
import com.game.core.lock.LockManager;
import com.game.msg.DispatchHandle;
import com.game.msg.IInnerReadable;
import com.game.msg.IMessageWritable;

public class Player implements IPlayer{
	
	
	private ChannelHandlerContext ctx;
	private long uid;
	
//	private String loginAccount;// 登录账户，用于创建角色的判断和记录
//	private int serverId;// 登录服务器的ID
//	private int loginUid;// 登录uid

	
	
	
//	/**
//	 * 登录账户，用于创建角色的判断和记录<br>
//	 * <font color="red"> 在开发测试服时，代表登录的用户名<br>
//	 * 在正式服经过平台登录流程时，代表是平台的唯一用户ID号。 </font>
//	 * 
//	 * @return
//	 */
//	public String getLoginAccount() {
//		return loginAccount;
//	}
//
//	public void setLoginUid(int loginUid) {
//		this.loginUid = loginUid;
//	}
//
//	/**
//	 * 服务器ID(唯一标识，这是服务器的技术ID标记)
//	 * 
//	 * @return
//	 */
//	public int getServerId() {
//		return serverId;
//	}
//
//	/**
//	 * 服务器ID(唯一标识，这是服务器的技术ID标记)
//	 * 
//	 * @param serverId
//	 */
//	public void setServerId(int serverId) {
//		this.serverId = serverId;
//	}
//
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

//	public void sendMessage(MsgParseHandle msg) {
//		try {
//			if (ctx != null) {
//
//				ByteBuffer bufferNew = getByteBufferFromGeneratedMessage(msg);
//				ctx.write(bufferNew);
//			} else {
//				if (this.getHero() == null) {
//					System.err.println("无法发送到客户端，该玩家的数据不存在hero="+ this.getHero());
//				} else {
//					System.err.println("无法发送到客户端，该玩家的客户端连接不存在uid="+ this.getHero().getUid());
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	// public void sendMsg(MsgGeneral msg){
	// if (ctx != null) {
	// Channel channel = ctx.getChannel();
	// if(channel!=null){
	// try{
	// ByteBuffer bufferNew =msg.getByteBufferFromGeneratedMessage();
	// ChannelFuture fh = channel.write(bufferNew);
	// fh.addListener(new ChannelFutureListener() {
	// @Override
	// public void operationComplete(ChannelFuture future) throws Exception {
	// future.getChannel().close();
	// // channel.close();
	// }
	// });
	// }catch (Exception e){
	// e.printStackTrace();
	// }
	// }
	// }
	// }
//	/**
//	 * 将协议数据转为buffer格式
//	 * 
//	 * @param msgInput
//	 */
//	private ByteBuffer getByteBufferFromGeneratedMessage(MsgParseHandle msgInput) {
//		GeneratedMessage msgBody = (GeneratedMessage) msgInput.getMsg().build();
//		byte[] src = msgBody.toByteArray();
//		ByteBuffer bufferNew = ByteBuffer.allocate(src.length + 12);
//		bufferNew.putInt(src.length + 4);
//		bufferNew.putInt(msgInput.getMsgCode());
//		bufferNew.put(src);
//		System.out.println((src.length + 12) + "发送数据uid="
//				+ (this.Hero != null ? Hero.getUid() : 0) + ",msgcode="
//				+ msgInput.getMsgCode());
//		System.out.println(msgInput);
//		return bufferNew;
//	}

	// /**
	// * 后台管理T人操作(清内存)
	// * @param message
	// */
	// public void sendMessageAwait(MsgGeneral message){ if (ctx != null) {
	// final Channel channel = ctx.getChannel();
	// ByteBuffer bufferNew = null;
	// try{
	// bufferNew = getByteBufferFromGeneratedMessage(message);
	// }catch (Exception e){
	// e.printStackTrace();
	// }
	// ChannelFuture fh = channel.write(bufferNew);
	// fh.addListener(new ChannelFutureListener() {
	// @Override
	// public void operationComplete(ChannelFuture future) throws Exception {
	// channel.close();
	// }
	// });
	//
	//
	// }else{
	// if(this.getUser()==null){
	// System.err.println("无法发送到客户端，该玩家的数据不存在hero="+ this.getUser());
	// }else{
	// System.err.println("无法发送到客户端，该玩家的客户端连接不存在uid="+ this.getUser().getUid());
	// }
	// }
	//
	// }

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
	 
	// /**
	// * 发送文本提示
	// * @param msgCode
	// */
	// public void sendMessage(String msgCode) {
	// MsgSCClientMsg msg = new MsgSCClientMsg();
	// SCClientMsg.SC_ClientMsg.Builder builder = msg.getMsg();
	// builder.setClientMsg(msgCode).build();
	// System.err.println(msgCode);
	// this.sendMessage(msg);
	// }

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
			ctx.writeAndFlush(byteBuf);
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



//	/**
//	 * 将协议发送到客户端
//	 * @param scMsg
//	 * @date 2016-4-15 下午5:03:15
//	 */
//	public void send2Client(GeneratedMessage scMsg) {
//		String codeName = scMsg.getClass().getName();
//		short code = MessageCode.getCode(codeName);
//		LogUtil.debug("userId:"+this.getUid()+"\tsend msg to client scMsg:"+scMsg.getClass().getSimpleName());
//		this.sendMessage(new SCMessage(code, scMsg));
//	}
	
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