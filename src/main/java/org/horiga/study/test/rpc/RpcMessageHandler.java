package org.horiga.study.test.rpc;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.horiga.study.test.rpc.message.Ping;
import org.horiga.study.test.rpc.message.Pong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RpcMessageHandler extends ChannelInboundHandlerAdapter {

	private static Logger log = LoggerFactory.getLogger(RpcMessageHandler.class);

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		log.debug("[channelRegistered]");
		super.channelRegistered(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		log.debug("[channelUnregistered]");
		super.channelUnregistered(ctx);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.debug("[channelActive]");
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		log.debug("[channelInactive]");
		super.channelInactive(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		log.debug("msg.name={}", msg.getClass().getName());
		if (msg instanceof Msg) {
			Msg message = (Msg)msg;
			Ping ping = (Ping)message.getBody();
			ctx.channel().write(new Pong(ping.getMsgid(), ping.getMessage())).addListener(ChannelFutureListener.CLOSE);
		} else {
			throw new IllegalArgumentException("unsupported message");
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		log.debug("[channelReadComplete]");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		log.warn("Thrift message failed.", cause);
	}
}
