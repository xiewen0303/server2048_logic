package com.game.nio.tcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import log.LogUtil;
import message.MessageCode;

import com.game.constant.GlobalConst;
import com.game.core.IPlayer;
import com.game.core.Player;
import com.game.msg.IMessageReadable;

public class TcpServerHandler extends  SimpleChannelInboundHandler<Object> {
	
	
	private static final AttributeKey<IPlayer> CHANNEL_SESSION_KEY = AttributeKey.valueOf(GlobalConst.CHANNEL_SESSION_KEY);
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object data) throws Exception {
		IMessageReadable messageReadable =  (IMessageReadable)data;
		short cmd = messageReadable.getMessageCode();
		IPlayer player = null;
		if(MessageCode.CS_Login == cmd){
			Player newPlayer = new Player();
			newPlayer.setCtx(ctx);
			ctx.attr(CHANNEL_SESSION_KEY).set(newPlayer);
			ctx.fireChannelRegistered();
			player = newPlayer;
		}else{ //如果是登录时可以创建player
			player = ctx.attr(CHANNEL_SESSION_KEY).get();
			if(player == null) {
				LogUtil.error("请先登录");
				return;
			}
			
			if(player.getUid() <= 0L){
				LogUtil.error("登录流程还未处理完！");
				return;
			}
		}

		messageReadable.dispatch(player);
	}
	
	/**
	 * 连接失效时会调用这个方法
	 */
	@Override
	public void  userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception{
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) evt;
			LogUtil.debug("close the channel: heartbeat {},type={}", ctx.channel(), event.state());
			ctx.close();
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		LogUtil.debug("Unexpected exception from downstream.", cause);
		IPlayer player = ctx.attr(CHANNEL_SESSION_KEY).get();
		if(player != null) {
			player.offline();
		}
		ctx.close();
	}


	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		LogUtil.debug("channelActive.........");
		super.channelActive(ctx);
	}
}
