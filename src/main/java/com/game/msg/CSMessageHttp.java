package com.game.msg;

import java.nio.ByteBuffer;

import com.game.core.IPlayer;
import com.game.nio.http.bak.MessageCode2;

/**
 * 读取协议
 * @author XieWen
 * @date 2016-3-15 下午4:08:32
 */
public class CSMessageHttp implements IMessageReadable {
	 
	private short code;
	private Object obj;
	private IPlayer player;
	
	public CSMessageHttp(int cmd, ByteBuffer data) {
		super();
		this.code = (short)code;
		this.obj = MessageCode2.getGenMessage2(code, data);
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
		return (T)obj;
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
