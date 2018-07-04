package com.game.gen;

/**
 * 类型
 * @author XieWen
 * @date 2015-9-25 上午10:00:04
 */
public enum PrimaryType {
	HERO(1) 		//玩家
	,PERSON(2) 		//拥有的英雄
	,MONSTER(3)		//怪物
	,ITEM(4)		//道具
	, MAIL(5)		//邮件
	, FIGHT_REPORT(6) //战报
	, BAOXIANG(7) //宝箱
	, RECHAGER(8) //充值
	, ROBOT(9) //机器人
	, GUILD(10) //行会
	;
	private int type;

	private PrimaryType(int type) {
		 this.type = type;
	}
	
	public int getType(){
		return type;
	}

	/**
	 * 是否为负数
	 * @return
     */
	public boolean isMinus(){
		if( this == ROBOT ){
			return true;
		}
		return false;
	}
}
