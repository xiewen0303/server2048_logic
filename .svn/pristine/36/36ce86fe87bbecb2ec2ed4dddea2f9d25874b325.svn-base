package com.game.nio.flashsafe;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import log.LogUtil;


/**
 * 连接管理器
 */
public class SafeServer {

	protected static final int BIZGROUPSIZE = 1;//Runtime.getRuntime().availableProcessors()*2;	//默认
	protected static final int BIZTHREADSIZE = 1;

	/**
	 * 接受accept 连接的线程组
	 * Netty的架构使用了非常复杂的主从式Reactor线程模型。简单的说就是。
	 * 父线程组（代码中的parentBosser）担任（acceptor）的角色。负责接收客户端的连接请求，处理完成请求，
	 * 创建一个Channel并注册到子线程组（代码中的childWorker）中的某个线程上面，然后这个线程将负责Channel的读写，编解码等操作。
	 */
	private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
	/**
	 * 处理业务，对io读与写
	 */
	private static final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);
	
	private static final int PORT = 843;

	public static void main(String[] args) {
		SafeServer.start();
	}

	/**
	 * 开启netty 服务
	 */
	public static void start(){
		try {
			ServerBootstrap bootStarp = new ServerBootstrap();
			bootStarp.group(bossGroup,workerGroup);
			bootStarp.channel(NioServerSocketChannel.class);

			
			bootStarp.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline pipeline = ch.pipeline();
					pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
					pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
					pipeline.addLast(new SafeServerHandler());
				}
			});
			
			bootStarp.option(ChannelOption.SO_BACKLOG, 128);
			bootStarp.option(ChannelOption.SO_TIMEOUT, 25);
			
			bootStarp.childOption(ChannelOption.SO_KEEPALIVE, true);
			bootStarp.childOption(ChannelOption.TCP_NODELAY, true);

//			bootStarp.bind(IP, PORT).sync();
			bootStarp.bind(PORT).sync();
			LogUtil.info("Safe service started,PORT["+PORT+"]");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//结束服务
	protected static void shutdown() {
		workerGroup.shutdownGracefully();
		bossGroup.shutdownGracefully();
	}
}


class SafeServerHandler extends ChannelInboundHandlerAdapter {
	public static final String 	xml = "<cross-domain-policy> "
			+ "<allow-access-from domain=\"*\" to-ports=\"*\"/>"
			+ "</cross-domain-policy>"
			+ "\0";
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		String info = String.valueOf(msg);
		LogUtil.debug("safe revice data===========:"+info);
		if (info.indexOf("policy-file-request") >= 0) {
			ChannelFuture future = ctx.writeAndFlush(xml);
			//future.get();//等待数据发送完成（阻塞）
			future.addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					future.channel().close(); //异步关闭
				}
			});
		}
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		LogUtil.info("843 channelActive.......");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		LogUtil.info("843 exceptionCaught.......");
	}
}