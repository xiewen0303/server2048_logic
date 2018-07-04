package com.game.mapping.constant;

/**
 * 指令组类型
 */
public enum EasyGroup {

	/**
	 * 自定义登录类型
	 */
	LOGING("login")
	/**
	 * 公共业务类型
	 */
	,PUBLIC("public")
	
	/**
	 * 业务初始化类型
	 */
	,BUS_INIT("bus_init")
	
	/**
	 * 业务缓存类型
	 */
	,BUS_CACHE("bus_cache")
	/**
	 * 场景业务类型
	 */
	,STAGE("stage")
	/**
	 * 场景控制类型
	 */
	,STAGE_CONTROL("stage_control")
	;
	
	
	private String groupName;
	
	private EasyGroup(String groupName){
		this.groupName = groupName;
	}

	public String getGroupName() {
		return groupName;
	}
}
