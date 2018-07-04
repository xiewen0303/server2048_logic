package com.game.kernel;

public interface IGroupExecutor {
	
	void clearExecutor();

	public ExecutorServiceContext getWorkExecutor(Object key);
}
