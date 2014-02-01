package org.horiga.study.test.rpc;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class RpcServerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast("thrift.rpc.decoder", new ThriftMessageDecoder());
		pipeline.addLast("thrift.rpc.encoder", new ThriftMessageEncoder());
		pipeline.addLast("rpc.handler", new RpcMessageHandler());
	}
}
