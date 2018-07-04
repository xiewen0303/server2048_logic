package com.game.mapping.manage;

import java.util.HashMap;
import java.util.Map;

import com.game.mapping.constant.EasyGroup;
import com.game.mapping.constant.EasyKuafuType;

public class CmdGroupInfo {

	private static Map<String, EasyGroup> moduleGroupMap = new HashMap<>();
	/**
	 * key:子模块,value:指令
	 */
	private static Map<Short, EasyGroup> cmdGroupMap = new HashMap<>();
	private static Map<Short, String> cmdModuleMap = new HashMap<>();
	
	/**
	 * 注册指令
	 * @param cmd
	 * @param moduleName
	 * @param groupName
	 * @param kuafuType
	 * @throws Exception 
	 */
	public static void registerCmds(short cmd,String moduleName,EasyGroup groupName,EasyKuafuType kuafuType) throws Exception{
		
		if(cmdGroupMap.containsKey(cmd)){
			throw new Exception("重复注册协议号 ["+cmd+","+moduleName+","+groupName.getGroupName()+"]");
		}
		
		cmdGroupMap.put(cmd, groupName);
		moduleGroupMap.put(moduleName,groupName);
		cmdModuleMap.put(cmd, moduleName);
		
		//跨服指令注册
		EasyKuafuCmdInfo.registerKuafuCmds(cmd, kuafuType);
	}
	
	
//	public static Integer getCmdDest(Short cmd){
//		return cmdDestMap.get(cmd);
//	}
	
	/**
	 * 获取指令所属执行分组
	 * @param cmd
	 * @return
	 */
	public static EasyGroup getCmdGroup(Short cmd){
		return cmdGroupMap.get(cmd);
	}
	
	/**
	 * 获取指令所属模块名
	 * @param cmd
	 * @return
	 */
	public static String getCmdModule(Short cmd){
		return cmdModuleMap.get(cmd);
	}
	 
	/**
	 * 判定一个指令是否属于某个模块
	 * @param cmd
	 * @param moduleName
	 * @return
	 */
	public static boolean isModule(Short cmd,String moduleName){
		String module = cmdModuleMap.get(cmd);
		if(null == module) return false;
		return module.equals(moduleName);
	}

	/**
	 * 判定一个模块是否属于某个执行分组
	 * @param module
	 * @param groupName
	 * @return
	 */
	public static boolean isGroup(String module,EasyGroup groupName){
		EasyGroup group = moduleGroupMap.get(module);
		if(null == group) return false;
		return group.equals(groupName);
	} 

}
