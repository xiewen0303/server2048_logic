package com.game.constant;


/**
 * 全局常量类 
 *
 */
public class GlobalConst {
	 
	
	/**
	 * 移动位数
	 */
	public static final int  MOVE_BIT = 46;
	
	/**
	 * 连接chanle的session
	 */
	public static final String CHANNEL_SESSION_KEY = "CHANNEL_SESSION_KEY";

	public static final int TONGYONG_EVENT_SOURCE = 1;
	
	public static final String DOU_HAO = ",";
	public static final String FEN_HAO = ";";
	public static final String MAO_HAO = ":";
	
	
	//怪物的启始ID20000
	public static final int MONSTER_START_ID = 20000;

	
	//宽
	public static final int SLOT_WIDTH = 6;
	//高
	public static final int SLOT_HEIGHT = 6;
	
	//最多的格子数
	public static final int SLOT_MAX = SLOT_WIDTH * SLOT_HEIGHT;
}
