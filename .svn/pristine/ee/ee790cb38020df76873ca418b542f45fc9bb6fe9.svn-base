package com.game.kernel;

import com.game.kernel.clearRule.IClearRule;

public class TestExecutor {
	
	
	public static void main(String[] args) {
		
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getId());
			}
		};
		
		TaskExecutors taskExecutors = new TaskExecutors(3);
		GroupExecutor groupExecutor = new GroupExecutor(new IClearRule() {
			
			@Override
			public boolean clear(Object key) {
				
				return false;
			}
		},taskExecutors);
		
		for (int i = 0; i < 10000; i++) {
			
			long uid = 100;
			ExecutorServiceContext executor = groupExecutor.getWorkExecutor(uid);
			executor.execute(runnable);
//			System.out.println(i);
		}
		
		
	}
}