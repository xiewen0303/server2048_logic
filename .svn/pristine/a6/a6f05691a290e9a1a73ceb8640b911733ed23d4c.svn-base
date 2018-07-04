package com.game.nio.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import log.LogUtil;

import com.game.config.ProperitesConfig;
import com.game.config.ProperitesConst;


/**
 * 连接管理器
 */
public class TCPServer {
	
	public static ServerBootstrap bootstrap = null;
	
	/**
	 * 连接管理器
	 */
//	public Connector(SimpleChannelHandler channelHandler,int port) {
//	    // Configure the server.
//        bootstrap = new ServerBootstrap(
//                new NioServerSocketChannelFactory(
//                        Executors.newCachedThreadPool(),
//                        Executors.newCachedThreadPool(),100));
//        // Configure the pipeline factory.
//        bootstrap.setPipelineFactory(new ServerPipelineFactory(channelHandler));
//        bootstrap.setOption("child.tcpNoDelay", true);  
//        bootstrap.setOption("child.keepAlive", true);  
//        bootstrap.setOption("connectTimeoutMillis","25");
//        // Bind and start to accept incoming connections.
//        bootstrap.bind(new InetSocketAddress(port));
//        logger.info("游戏服务器端口为" + port);
//	}

	protected static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors()*2;	//默认
	protected static final int BIZTHREADSIZE = 4;

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
	
//	private static final String IP = "192.168.0.33";
	private static final int PORT = ProperitesConfig.getInt(ProperitesConst.SERVER_BASE, "SERVER_PORT");
	
	
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
					/**
					 * 120秒心跳循环check  如果间隔时间超过120秒没有协议读写，那么会调用  userEventTriggered 方法
					 */
					pipeline.addLast("idle", new IdleStateHandler(0,0,120));

					pipeline.addLast("decoder", new Decoder());
					pipeline.addLast("encoder", new Encoder());
//					pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
//					pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
					pipeline.addLast(new TcpServerHandler());
				}
			});


			/**
			 * 具体为：
			 ChannelOption.SO_BACKLOG, 1024
			 BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50。

			 ChannelOption.SO_KEEPALIVE, true
			 当设置为true的时候，TCP会实现监控连接是否有效，当连接处于空闲状态的时候，超过了2个小时，本地的TCP实现会发送一个数据包给远程的 socket，如果远程没有发回响应，TCP会持续尝试11分钟，知道响应为止，如果在12分钟的时候还没响应，TCP尝试关闭socket连接。

			 ChannelOption.TCP_NODELAY, true
			 在TCP/IP协议中，无论发送多少数据，总是要在数据前面加上协议头，同时，对方接收到数据，也需要发送ACK表示确认。为了尽可能的利用网络带宽，TCP总是希望尽可能的发送足够大的数据。这里就涉及到一个名为Nagle的算法，该算法的目的就是为了尽可能发送大块数据，避免网络中充斥着许多小数据块。
			 TCP_NODELAY就是用于启用或关于Nagle算法。如果要求高实时性，有数据发送时就马上发送，就将该选项设置为true关闭Nagle算法；如果要减少发送次数减少网络交互，就设置为false等累积一定大小后再发送。默认为false。
			 */
			bootStarp.option(ChannelOption.SO_BACKLOG, 1024);
			bootStarp.option(ChannelOption.SO_TIMEOUT, 25);
			bootStarp.option(ChannelOption.TCP_NODELAY, true);


			bootStarp.childOption(ChannelOption.SO_KEEPALIVE, true);

//			bootStarp.bind(IP, PORT).sync();
			bootStarp.bind(PORT).sync();
			LogUtil.info("TCP服务器已启动,PORT["+PORT+"]");
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

