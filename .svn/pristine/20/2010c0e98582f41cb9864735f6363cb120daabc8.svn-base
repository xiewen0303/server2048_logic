package com.game.kernel.dispatch;
  

import com.game.kernel.ExecutorServiceContext;
import com.game.kernel.GroupExecutor;
import com.game.kernel.IGroupExecutor;
import com.game.kernel.TaskExecutors;
import com.game.kernel.clearRule.IClearRule;
import com.game.mapping.manage.GotoManager;
import com.game.mapping.resolver.IResolver;
import com.game.msg.IMessageReadable;

public class CommonDispatch implements IDispatch {
	
	private IGroupExecutor groupExecutor;
	
	/**
	 * 初始化
	 * @param poolSize  线程数
	 * @param clearRule 清除规则
	 */
	public CommonDispatch(int poolSize,IClearRule clearRule) {
		this.groupExecutor = new GroupExecutor(clearRule, new TaskExecutors(poolSize));
	}
	
	/**
	 * 信息转发
	 * @param groupInfo
	 * @param msg
	 */
	public void dispatch(final Object groupInfo,final IMessageReadable msg) {
		final short cmd = msg.getMessageCode();
		ExecutorServiceContext executor = groupExecutor.getWorkExecutor(groupInfo);
		executor.execute(new Runnable() {
			@Override
			public void run() {
				IResolver resolver = GotoManager.getResolver(cmd);
				resolver.execute(msg);
			}
		});
	}
}