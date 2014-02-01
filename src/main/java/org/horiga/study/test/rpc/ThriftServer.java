package org.horiga.study.test.rpc;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.internal.SystemPropertyUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThriftServer {

	private static Logger log = LoggerFactory.getLogger(ThriftServer.class);

	private final int port;

	private EventLoopGroup boss = new NioEventLoopGroup();
	private EventLoopGroup worker = new NioEventLoopGroup(); // TODO custom

	public ThriftServer() {
		this.port = SystemPropertyUtil.getInt("default.rpc.port", 19000);
	}

	public void run() throws Exception {

		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(boss, worker).channel(NioServerSocketChannel.class).childHandler(new RpcServerInitializer());

			// bootstrap.childOption(...)

			Channel ch = bootstrap.bind(port).sync().channel();

			log.info(">> STARTUP COMPLETED.");

			ch.closeFuture().sync();

		} finally {
			boss.shutdownGracefully();
			worker.shutdownGracefully();
		}
	}

	public static void main(String[] args) {
		try {
			new ThriftServer().run();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
