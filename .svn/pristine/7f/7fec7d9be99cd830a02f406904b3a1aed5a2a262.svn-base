package com.game.core.excutehandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogExecutorService {
	
	private static ExecutorService  excutor = Executors.newFixedThreadPool(1);
	
	public static void execute(Runnable command){
		if(!excutor.isShutdown()){
			excutor.execute(command);	
		}
	}
	
	public static void shutdown(){
		excutor.shutdown();
	}
}
