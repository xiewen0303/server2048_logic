package com.game.core.excutehandler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.game.msg.IInnerReadable;


public class DispatchHandlerbak {
	
	public static ConcurrentHashMap<Object,ExecutorService> executorServices = new ConcurrentHashMap<Object,ExecutorService>();
 
	
	public static void dispatch(String moduleName,Runnable runnable){
		ExecutorService excutor = executorServices.get(moduleName);
		if(excutor == null){
			excutor = Executors.newSingleThreadExecutor(new GameThreadFactory(moduleName));//newFixedThreadPool(1);
			executorServices.put(moduleName, excutor);
		}
		excutor.execute(runnable);
	}
	
	
	public static void dispatchLog(Runnable runnable) {
		LogExecutorService.execute(runnable);
	}
	
	/**
	 * 内部模块之间的调用(修改其他模块store,请调用该方法)
	 * @param innerMessage
	 */
	public static void dispatchInner(final IInnerReadable innerMessage)
	{
		String moduleName = innerMessage.getModuleName();
		ExecutorService excutor = executorServices.get(moduleName);
		if(excutor == null){
			excutor = Executors.newSingleThreadExecutor(new GameThreadFactory(moduleName));
			executorServices.put(moduleName, excutor);
		}
		excutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					innerMessage.handle();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}