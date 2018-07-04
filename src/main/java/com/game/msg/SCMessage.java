package com.game.msg;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessageV3;

/**
 * 读取协议
 * @author XieWen
 * @date 2016-3-15 下午4:08:32
 */
public class SCMessage implements IMessageWritable {
	private short code;
	private int traceId;
	private byte[] datas;
	
	public SCMessage(short code, GeneratedMessage bytes) {
		this.code = code;
		this.datas = bytes.toByteArray();
	}
	
	public SCMessage(short code, GeneratedMessageV3 bytes) {
		this.code = code;
		this.datas = bytes.toByteArray();
	}

	@Override
	public ByteBuf write() {
		ByteBuf bufferNew = PooledByteBufAllocator.DEFAULT.heapBuffer(datas.length+12);
		bufferNew.writeInt(datas.length+8);
		bufferNew.writeInt(traceId); //MsgCode
		bufferNew.writeInt(code); //MsgCode
		bufferNew.writeBytes(datas);
		return bufferNew;
	}
}
