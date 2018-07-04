package com.game.msg;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import com.game.msg.gen.MsgRevHead;
import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessageV3;

/**
 * 读取协议
 * @author XieWen
 * @date 2016-3-15 下午4:08:32
 */
public class SCMessageWS implements IMessageWritable {
	private short messageId;
	private String errorMsg ="";
//	private byte[] datas;
	private int appCode;
	private ByteString dataByteString;
	
//	public SCMessageWS(short code, GeneratedMessage bytes) {
//		this.code = code;
//		this.datas = bytes.toByteArray();
//	}
	
	public SCMessageWS(short messageId, GeneratedMessageV3 bytes,int appCode) {
		this.messageId = messageId;
//		this.datas = bytes.toByteArray();
		this.dataByteString = bytes.toByteString();
		this.appCode = appCode;
		
	}

	@Override
	public ByteBuf write() {
		
		MsgRevHead.MsgBase.Builder ack = MsgRevHead.MsgBase.newBuilder();
		ack.setMsgID(this.messageId);
		ack.setErrorMsg(this.errorMsg);
		ack.setResult(this.appCode);
		ack.setData(dataByteString);
		
		byte[] src = ack.build().toByteArray();
		ByteBuf bufferNew = PooledByteBufAllocator.DEFAULT.heapBuffer(src.length);
		bufferNew.writeBytes(src);
		return bufferNew;
	}
}
