package com.game.kernel;


import log.LogUtil;

public class TaskExecutors {
	
	public ExecutorServiceContext[] executors;
	
	/**
	 * 任务池线程数
	 * @param size
	 */
	public TaskExecutors(int size){
		executors = new ExecutorServiceContext[size];
		for (int i = 0; i < size; i++) {
			executors[i] =  new ExecutorServiceContext();
		}
	}
	
	/**
	 * 获得对应的线程池
	 * @return 
	 * @date 2016-3-29 下午6:03:45
	 */
	public ExecutorServiceContext getExecutorService(){
		if(executors == null) {
			LogUtil.error("TaskExecutors no init!");
			return null;
		}
		ExecutorServiceContext result = executors[0];
		
		int loadTemp = result.getLoad();
		for (ExecutorServiceContext serviceContext : executors) {
			if(loadTemp > serviceContext.getLoad()){
				result = serviceContext;
			}
		}
		return result;
	}
}