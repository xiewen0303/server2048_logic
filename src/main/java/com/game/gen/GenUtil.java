package com.game.gen;

public class GenUtil {
	
	/**
	 * 获得自增主键
	 * @param primaryType {@link PrimaryType}
	 * @return
	 */
	public static long getGenKey(PrimaryType primaryType){
		return PrimaryKeyGen.getPrimaryKeyGen().getPk(primaryType,true);
	}

	/**
	 * 获得自增主键
	 * @param primaryType {@link PrimaryType}
	 * @return
	 */
	public static long getGenKeyNoStore(PrimaryType primaryType){
		return PrimaryKeyGen.getPrimaryKeyGen().getPk(primaryType,false);
	}
	
//	/**
//	 * 登录时自增
//	 * @return
//	 */
//	public static long getSignKey(){
//		return AutoGen.timeStampGenerator.getStamp();
//	}
}
