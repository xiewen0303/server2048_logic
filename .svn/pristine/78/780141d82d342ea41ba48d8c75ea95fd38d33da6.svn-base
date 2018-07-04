package com.game.msg;

import message.MessageCode;

import com.game.core.IPlayer;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessageV3;

/**
 * 读取协议
 * @author XieWen
 * @date 2016-3-15 下午4:08:32
 */
public class CSMessage implements IMessageReadable {
	 
	private short code;
	private GeneratedMessageV3 messageContent;
	private IPlayer player;
	
	public CSMessage(short code, byte[] bytes) {
		super();
		this.code = code;
		this.messageContent = MessageCode.getGenMessage(code, bytes);
	}
	
	@Override
	public void dispatch(IPlayer player){
		this.player = player;
		DispatchHandle.dispatch(this);
	}

	@Override
	public short getMessageCode() {
		return code;
	}

	@Override
	public <T> T getMessage() {
		return (T)messageContent;
	}

	@Override
	public long getUid() {
		return player.getUid();
	}

	@Override
	public IPlayer getIPlayer() {
		return player;
	}
}
