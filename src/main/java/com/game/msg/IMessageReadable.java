package com.game.msg;

import com.game.core.IPlayer;
import com.google.protobuf.GeneratedMessage;

/**
 * 协议载体
 * @author XieWen
 * @date 2016-4-14 下午5:29:13
 */
public interface IMessageReadable {
	
	/**
	 * 获取协议号
	 * @return
	 */
	public short getMessageCode();
	
	/**
	 * 获得协议体信息
	 * @return 
	 * @date 2016-4-18 上午10:06:37
	 */
	public <T> T getMessage();
	
	/**
	 * 转发
	 * @param player
	 */
	public void dispatch(IPlayer player);
	
	/**
	 * 获取连接信息
	 * @date 2016-4-18 上午10:05:55
	 */
	public IPlayer getIPlayer();
	
	/**
	 * 玩家信息
	 * @return 
	 * @date 2016-4-18 上午10:36:44
	 */
	public long getUid();
}