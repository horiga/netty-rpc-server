package org.horiga.study.test.rpc;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.thrift.TBase;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TTransport;
import org.horiga.study.test.rpc.message.MsgHeader;
import org.horiga.study.test.rpc.message.Ping;
import org.horiga.study.test.rpc.message.Pong;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test for simple App.
 */
public class AppTest {

	private static Logger log = LoggerFactory.getLogger(AppTest.class);

	@Test
	public void pingpong() {

		try {

			Socket soc = new Socket("localhost", 19000);

			InputStream is = soc.getInputStream();
			OutputStream os = soc.getOutputStream();

			TTransport transport = new TIOStreamTransport(is, os);
			TProtocol protocol = new TBinaryProtocol(transport);

			MsgHeader header = new MsgHeader();
			header.name = "ping";
			header.txid = 1;
			header.write(protocol);

			Ping ping = new Ping();
			ping.msgid = 1;
			ping.message = "echo";
			ping.write(protocol);

			// receive response

			TProtocol _protocol = new TBinaryProtocol(new TIOStreamTransport(is));
			TBase<?, ?> pong = new Pong();
			pong.read(_protocol);

			log.debug("RECEIVED MESSAGE = {}", ((Pong)pong).message);

			os.close();
			is.close();
			soc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
