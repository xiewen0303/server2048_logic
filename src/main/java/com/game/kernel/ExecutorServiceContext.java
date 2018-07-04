package com.game.kernel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 *  static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(6, 10, 1000, TimeUnit.MILLISECONDS, workQueue);
 * 	corePoolSize
 *	核心线程数，核心线程会一直存活，即使没有任务需要处理。当线程数小于核心线程数时，即使现有的线程空闲，线程池也会优先创建新线程来处理任务，而不是直接交给现有的线程处理。
 *	核心线程在allowCoreThreadTimeout被设置为true时会超时退出，默认情况下不会退出。
 *	maxPoolSize
 *	当线程数大于或等于核心线程，且任务队列已满时，线程池会创建新的线程，直到线程数量达到maxPoolSize。如果线程数已等于maxPoolSize，且任务队列已满，则已超出线程池的处理能力，线程池会拒绝处理任务而抛出异常。
 *	keepAliveTime
 *	当线程空闲时间达到keepAliveTime，该线程会退出，直到线程数量等于corePoolSize。如果allowCoreThreadTimeout设置为true，则所有线程均会退出直到线程数量为0。
 *	allowCoreThreadTimeout
 *	是否允许核心线程空闲退出，默认值为false。
 *	queueCapacity
 *	任务队列容量。从maxPoolSize的描述上可以看出，任务队列的容量会影响到线程的变化，因此任务队列的长度也需要恰当的设置。
 */



/**
 * 执行池
 * @author XieWen
 * @date 2016-3-29 下午5:59:07
 */
public class ExecutorServiceContext {
	
	private int load; //不能超过int最大值,
	
	private ExecutorService executorService;

	public ExecutorServiceContext(){
		executorService = Executors.newSingleThreadExecutor(new CoreThreadFactory("business"));
	}
	
	public synchronized int getLoad() {
		return load;
	}
	
	public synchronized void addLoad() {
			++load;
	}
	
	 public void execute(Runnable runnable){
		 executorService.execute(runnable);
	 }


	public synchronized void lowerLoad() {
		if(load>0){
			--load;
		}
	}
}
