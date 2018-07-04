package com.game.msg;

/**
 * 	内部协议跳转
 *  注：这里完全可以使用注解方式实现,时间有限,先手写。
 *  包括XXXCtl类可删掉 ,service可以不用写到Services中的静态属性中去,这样避免了直接调用 引起的并发错误。
 *  当做下一个版本的修改目标 大致思路是在方法前面自定义注解内部协议code码，以此为寻找目标
 * @author XieWen
 * @date 2015-10-27 下午5:20:51
 */
public interface IInnerReadable {
	/**
	 * 长连接可以将IPlayer传入 ,短连接没啥用 @param ctx   
	 * @throws Exception
	 */
	void handle() throws Exception;
	
	/**
	 * 获得模块名称
	 * @return
	 */
	String getModuleName();
}
