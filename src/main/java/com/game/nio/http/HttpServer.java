package com.game.nio.http;

import log.LogUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class HttpServer {
//	public static void main(String[] args) {
//		HttpServer.start();
//	}


//	public static final String IP="192.168.0.33";
	
	public static final int PORT = 8888;
	
	public static void start() {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
				b.group(bossGroup, workerGroup);
				b.channel(NioServerSocketChannel.class);
				b.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch)
								throws Exception {
							// server端发送的是httpResponse，所以要使用HttpResponseEncoder进行编码
							ch.pipeline().addLast(new HttpResponseEncoder());
							// server端接收到的是httpRequest，所以要使用HttpRequestDecoder进行解码
							ch.pipeline().addLast(new HttpRequestDecoder());
							ch.pipeline().addLast(new HttpServerInboundHandler());
						}
					});
				
					b.option(ChannelOption.SO_BACKLOG, 128);
					b.childOption(ChannelOption.SO_KEEPALIVE, true);

			ChannelFuture f = b.bind(PORT).sync();
			LogUtil.info("HTTP服务器已启动！端口号："+PORT);
			
			
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();  
		}
	}
}
