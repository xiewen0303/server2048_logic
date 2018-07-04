package com.game.nio.websocket;

import com.game.config.ProperitesConfig;
import com.game.config.ProperitesConst;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import log.LogUtil;

public class WebSocketServer {
	
	/**
	 * 服务器端口
	 */
	private static final int PORT = ProperitesConfig.getInt(ProperitesConst.SERVER_BASE, "SERVER_PORT");
	/**
	 * 心跳时间(单位：秒)
	 */
	private static int heartBeat = 120;
	
	public static void start() {  
	        EventLoopGroup bossGroup = new NioEventLoopGroup();  
	        EventLoopGroup workerGroup = new NioEventLoopGroup();  
	        try {  
	            ServerBootstrap b = new ServerBootstrap();  
	            b.group(bossGroup, workerGroup);
	            b.channel(NioServerSocketChannel.class);
	            b.childOption(ChannelOption.SO_KEEPALIVE, true);
                b.childOption(ChannelOption.TCP_NODELAY, true);
	            b.childOption(ChannelOption.SO_RCVBUF, 65536);
                b.childOption(ChannelOption.SO_SNDBUF, 65536);
//	            b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
	            b.childOption(ChannelOption.ALLOCATOR, new PooledByteBufAllocator(false));  // heap buf 's better
	            b.option(ChannelOption.SO_BACKLOG, 1024);
	            
	            b.childHandler(new ChannelInitializer<Channel>() {  
	                @Override  
	                protected void initChannel(Channel channel) throws Exception {  
	                    ChannelPipeline pipeline = channel.pipeline();  
	                    
	                    pipeline.addLast("idle", new IdleStateHandler(0, 0, heartBeat));
	                    pipeline.addLast("http-codec", new HttpServerCodec()); // Http消息编码解码  
	                    pipeline.addLast("aggregator", new HttpObjectAggregator(65536)); // Http消息组装  
	                    pipeline.addLast("http-chunked", new ChunkedWriteHandler()); // WebSocket通信支持
//	                    pipeline.addLast(new WebSocketServerProtocolHandler("/")); // WebSocket通信支持
	                    pipeline.addLast("handler", new WebSocketServerHandler()); // WebSocket服务端Handler  
	                }  
	            });
	              
	            Channel channel = b.bind(PORT).sync().channel();  
	            LogUtil.info("WebSocket 已经启动，端口：" + PORT + ".");  
	            channel.closeFuture().sync();  
	        }catch (Exception e) {
	        	LogUtil.error("websocket 启动错误",e);
				e.printStackTrace();
			} finally {  
	            bossGroup.shutdownGracefully();  
	            workerGroup.shutdownGracefully();  
	        }  
	    }  
	
}
