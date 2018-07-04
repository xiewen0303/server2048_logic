package com.game.nio.http.bak;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders.Values;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

import java.nio.ByteBuffer;
import java.util.List;

import com.game.config.ProperitesConfig;
import com.game.config.ProperitesConst;
import com.game.msg.IMessageWritable;
import com.game.nio.http.Base64Util;

	/**
	 * 	Access-Control-Allow-Origin: <origin> | * // 授权的源控制
   		Access-Control-Max-Age: <delta-seconds> // 授权的时间
		Access-Control-Allow-Credentials: true | false // 控制是否开启与Ajax的Cookie提交方式
   		Access-Control-Allow-Methods: <method>[, <method>]* // 允许请求的HTTP Method
   		Access-Control-Allow-Headers: <field-name>[, <field-name>]* // 控制哪些header能发送真正的请求   
	 */
/**
 * 发送协议
 * @author XieWen
 * @date 2015-12-14 上午9:54:31
 */
public class SendUtil {
	/**
	 * 发送消息
	 * @param msg
	 * @param ctx
	 */
	public static void sendOne(ChannelHandlerContext ctx,IMessageWritable msg) {
		String base64Str = "";
		try {
			if(msg != null){
				ByteBuf byteBuffer = msg.write();
				base64Str = Base64Util.encoder(byteBuffer.array());
			}
			FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK, Unpooled.wrappedBuffer(base64Str.getBytes("UTF-8")));
			response.headers().set("CONTENT_TYPE", "text/plain;charset=UTF-8");
			response.headers().set("CONTENT_LENGTH", response.content().readableBytes());
			int isOpen = ProperitesConfig.getInt(ProperitesConst.SERVER_BASE, "OPEN_KY");
			if(isOpen == 1) {
				response.headers().set("Access-Control-Allow-Origin", "*");	
			}
			response.headers().set("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
				
				
//			response.headers().set("CONNECTION", Values.KEEP_ALIVE);
			response.headers().set("CONNECTION",  Values.CLOSE);
//			if (HttpHeaders.isKeepAlive(request)) {
//			}
			 
			 
			ChannelFuture future = ctx.writeAndFlush(response); 
			//future.get();//等待数据发送完成（阻塞）
			future.addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					future.channel().close(); //异步关闭
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static byte[] mergeBytes(byte[] data1,byte[] data2){
		byte[] data3 = new byte[data1.length+data2.length];
		System.arraycopy(data1,0,data3,0,data1.length);
		System.arraycopy(data2,0,data3,data1.length,data2.length);
		return data3;
	}
	 
	
	/**
	 * 发送消息
	 * @param msg
	 * @param ctx
	 */
	public static void send(ChannelHandlerContext ctx,List<IMessageWritable> msg) {
		String base64Str = "";
		
		try {
			 if(msg != null && msg.size()> 0) {
				byte[] bytes = new byte[0];
				for (IMessageWritable message : msg) {
					bytes = mergeBytes(bytes, message.write().array());
				}
				base64Str = Base64Util.encoder(bytes);
			 }
		 
			FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK, Unpooled.wrappedBuffer(base64Str.getBytes("UTF-8")));
			response.headers().set("CONTENT_TYPE", "text/plain;charset=UTF-8");
			response.headers().set("CONTENT_LENGTH", response.content().readableBytes());
			int isOpen = ProperitesConfig.getInt(ProperitesConst.SERVER_BASE, "OPEN_KY");
			if(isOpen == 1) {
				response.headers().set("Access-Control-Allow-Origin", "*");	
			}
			response.headers().set("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
				
				
//			response.headers().set("CONNECTION", Values.KEEP_ALIVE);
			response.headers().set("CONNECTION", Values.CLOSE);
//			if (HttpHeaders.isKeepAlive(request)) {
//			}
			ChannelFuture future = ctx.writeAndFlush(response);
			
			//future.get();//等待数据发送完成（阻塞）
			future.addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					future.channel().close(); //异步关闭
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 发送消息
	 * @param msg
	 * @param ctx
	 */
	public static void sendString(ChannelHandlerContext ctx,String msg) {
		try {
			FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK, Unpooled.wrappedBuffer(msg.getBytes("UTF-8")));
			response.headers().set("CONTENT_TYPE", "text/plain;charset=UTF-8");
			response.headers().set("CONTENT_LENGTH", response.content().readableBytes());
			int isOpen = ProperitesConfig.getInt(ProperitesConst.SERVER_BASE, "OPEN_KY");
			if(isOpen == 1) {
				response.headers().set("Access-Control-Allow-Origin", "*");	
			}
			response.headers().set("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
			
//			response.headers().set("CONNECTION", Values.KEEP_ALIVE);
			response.headers().set("CONNECTION", Values.CLOSE);
//			if (HttpHeaders.isKeepAlive(request)) {
//			}
			ChannelFuture future = ctx.writeAndFlush(response);
			
			//future.get();//等待数据发送完成（阻塞）
			future.addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					future.channel().close(); //异步关闭
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
