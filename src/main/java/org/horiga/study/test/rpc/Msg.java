package org.horiga.study.test.rpc;

import org.apache.thrift.TBase;
import org.horiga.study.test.rpc.message.MsgHeader;

public class Msg {

	private final MsgHeader header;
	private final TBase<?, ?> body;

	public Msg(MsgHeader header, TBase<?, ?> body) {
		this.header = header;
		this.body = body;
	}

	public MsgHeader getHeader() {
		return header;
	}

	public TBase<?, ?> getBody() {
		return body;
	}
}
