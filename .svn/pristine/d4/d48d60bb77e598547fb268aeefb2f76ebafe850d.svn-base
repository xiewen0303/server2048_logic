package com.game.gen;

/**
 * 保证当前内存中是唯一的
 */
public class AutoGen {
	
	public static AutoGen timeStampGenerator = new AutoGen();
	
	private static long lastId = 0;
	/**
	 * @return long
	 * 生成的唯一时间戳 (微秒数的时间戳)
	 */
	public  synchronized long getStamp(){
		return ++lastId;
	}
}
