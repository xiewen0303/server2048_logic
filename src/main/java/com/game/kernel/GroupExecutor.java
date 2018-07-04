package com.game.kernel;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.game.core.HeartBeat;
import com.game.core.HeartBeatorSource;
import com.game.kernel.clearRule.IClearRule;

/**
 * 工作组
 * @author XieWen
 * @date 2016-4-1 下午6:31:39
 */
public class GroupExecutor implements IGroupExecutor {
	
	/**
	 *  玩家ID 或者场景Id等
	 */
	private Map<Object,ExecutorServiceContext> groupExecutors = new ConcurrentHashMap<>();
	
	/**
	 * 过期的规则
	 */
	private IClearRule clearRule;
	
	/**
	 * 工作的线程组
	 */
	private TaskExecutors taskExecutors;  
	
	public GroupExecutor(IClearRule clearRule,TaskExecutors taskExecutors){
		this.clearRule = clearRule;
		this.taskExecutors = taskExecutors;
	}
	
	/**
	 * 获得工作线程
	 * @param key
	 * @return 
	 * @date 2016-4-1 下午5:51:11
	 */
	@Override
	public ExecutorServiceContext getWorkExecutor(Object key){
		ExecutorServiceContext  executorServiceContext = groupExecutors.get(key);
		if(executorServiceContext == null) {
			executorServiceContext = taskExecutors.getExecutorService();
			groupExecutors.put(key, executorServiceContext);
			//增加计数器
			executorServiceContext.addLoad();
			//添加定时check，降低计数器
			HeartBeatorSource.add(new HeartBeat(60*1000,5*60*1000) {
				@Override
			public void tick() {
				clearExecutor();
			}
		});
		}
		return executorServiceContext;
	}

	/**
	 * 清理没有占用的执行池引用
	 */
	@Override
	public void clearExecutor() {
		
		Iterator<Entry<Object, ExecutorServiceContext>> iterator = groupExecutors.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<Object, ExecutorServiceContext> entry = iterator.next();
			Object key = entry.getKey();
			boolean flag = clearRule.clear(key);
			if(flag){
				entry.getValue().lowerLoad();
				iterator.remove();
			}
		}
	}
}