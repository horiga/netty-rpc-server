package org.horiga.study.test.rpc;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import org.apache.thrift.TBase;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThriftMessageEncoder extends MessageToByteEncoder<TBase<?, ?>> {

	private static Logger log = LoggerFactory.getLogger(ThriftMessageEncoder.class);

	@Override
	protected void encode(ChannelHandlerContext ctx, TBase<?, ?> msg, ByteBuf buffer) throws Exception {
		final TTransport transport = new TIOStreamTransport(new ByteBufOutputStream(buffer));
		final TProtocol protocol = new TBinaryProtocol(transport);
		msg.write(protocol);
		transport.flush();

		log.debug("message flushed!!");
	}

}
