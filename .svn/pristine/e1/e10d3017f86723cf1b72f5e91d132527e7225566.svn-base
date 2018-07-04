package com.game.nio.websocket;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;
import log.LogUtil;
import message.MessageCode;

import com.game.constant.GlobalConst;
import com.game.core.IPlayer;
import com.game.core.PlayerForWS;
import com.game.msg.CSMessage;
import com.game.msg.IMessageReadable;
import com.game.msg.gen.MsgSendHead;
import com.google.protobuf.ByteString;

public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {
	
	private WebSocketServerHandshaker handshaker;
	
	private static final AttributeKey<IPlayer> CHANNEL_SESSION_KEY = AttributeKey.valueOf(GlobalConst.CHANNEL_SESSION_KEY);
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		LogUtil.debug("coming connection...");
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		if(msg instanceof TextWebSocketFrame){
			 String request = ((TextWebSocketFrame) msg).text();  
			 System.out.println(ctx.channel()+" received" + request);  
		}
		// 传统的HTTP接入
		else if (msg instanceof FullHttpRequest) {
			handleHttpRequest(ctx, (FullHttpRequest) msg);
		}
		// WebSocket接入
		else if (msg instanceof WebSocketFrame) {
			handleWebSocketFrame(ctx, (WebSocketFrame) msg);
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	private void handleHttpRequest(ChannelHandlerContext ctx,
			FullHttpRequest req) throws Exception {

		// 如果HTTP解码失败，返回HHTP异常
		if (!req.getDecoderResult().isSuccess()
				|| (!"websocket".equals(req.headers().get("Upgrade").toLowerCase()))) {
			sendHttpResponse(ctx, req, new DefaultFullHttpResponse(
					HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
			return;
		}

		// 构造握手响应返回，本机测试
		WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://127.0.0.1:8801", null, false);
		handshaker = wsFactory.newHandshaker(req);
		if (handshaker == null) {
//			WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
			WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
		} else {
			handshaker.handshake(ctx.channel(), req);
		}
	}

	private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {

		// 判断是否是关闭链路的指令
		if (frame instanceof CloseWebSocketFrame) {
			handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
			return;
		}
		// 判断是否是Ping消息
		if (frame instanceof PingWebSocketFrame) {
			ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
			return;
		}
		
		
		//二进制
		if(frame instanceof BinaryWebSocketFrame){
			
			BinaryWebSocketFrame b = (BinaryWebSocketFrame)frame;
			ByteBuf byteBuf = b.content();
			int len = byteBuf.readableBytes();
			byte[] req = new byte[len];
			byteBuf.readBytes(req);
			
			IMessageReadable messageReadable = null;
			short cmd = 0;
			try {
				MsgSendHead.MsgBase msgBase = MsgSendHead.MsgBase.parseFrom(req);
				int msgCode = msgBase.getMsgId();
				ByteString contentByteStr = msgBase.getData();
				messageReadable = new CSMessage((short) msgCode,contentByteStr.toByteArray());
				cmd = messageReadable.getMessageCode();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			IPlayer player = null;
			if(MessageCode.CS_Login == cmd){
				IPlayer newPlayer = new PlayerForWS();
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
				
				if(player.getAccountId() == null){
					LogUtil.error("登录流程还未处理完！");
					return;
				}
			}

			messageReadable.dispatch(player);
		
			return;
		}
//		
//		// 本例程仅支持文本消息，不支持二进制消息
//		if (!(frame instanceof TextWebSocketFrame)) {
//			LogUtil.error("{} frame types not supported", frame.getClass().getName());
//			throw new UnsupportedOperationException(String.format( "%s frame types not supported", frame.getClass().getName()));
//		}
		
		LogUtil.error("{} frame types not supported", frame.getClass().getName());
		
//		// 返回应答消息
//		String request = ((TextWebSocketFrame) frame).text();
//		// if (logger.isLoggable(Level.FINE)) {
//		// logger.fine(String.format("%s received %s", ctx.channel(), request));
//		// }
//		ctx.channel().write(new TextWebSocketFrame(request + " , 欢迎使用Netty WebSocket服务，现在时刻：" + new java.util.Date().toString()));
	}

	private static void sendHttpResponse(ChannelHandlerContext ctx,FullHttpRequest req, FullHttpResponse res) {
		// 返回应答给客户端
		if (res.getStatus().code() != 200) {
			ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(),CharsetUtil.UTF_8);
			res.content().writeBytes(buf);
			buf.release();
			HttpHeaders.setContentLength(res, res.content().readableBytes());
		}

		// 如果是非Keep-Alive，关闭连接
		ChannelFuture f = ctx.channel().writeAndFlush(res);
		if (!HttpHeaders.isKeepAlive(req) || res.getStatus().code() != 200) {
			f.addListener(ChannelFutureListener.CLOSE);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	
	@Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("close......");
    }
}