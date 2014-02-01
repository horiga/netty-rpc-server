package org.horiga.study.test.rpc;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DecoderException;

import java.util.List;

import org.apache.thrift.TBase;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TIOStreamTransport;
import org.horiga.study.test.rpc.message.MsgHeader;
import org.horiga.study.test.rpc.message.Ping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThriftMessageDecoder extends ByteToMessageDecoder {

	private static Logger log = LoggerFactory.getLogger(ThriftMessageDecoder.class);

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		final TProtocol protocol = new TBinaryProtocol(new TIOStreamTransport(new ByteBufInputStream(in)));

		final TBase<?, ?> message = new MsgHeader();
		message.read(protocol);

		log.debug("msg.name={}", ((MsgHeader)message).name);

		if ("ping".equals(((MsgHeader)message).name)) {
			final TBase<?, ?> body = new Ping();
			body.read(protocol);
			out.add(new Msg((MsgHeader)message, body));
			return;
		}

		throw new DecoderException("invalid message protcols");
	}
}
